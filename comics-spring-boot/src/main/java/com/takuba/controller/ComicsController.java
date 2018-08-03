package com.takuba.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.takuba.bean.Biblioteca;
import com.takuba.exception.BibliotecaNotFoundException;
import com.takuba.exception.CalendarioNotFoundException;
import com.takuba.exception.ComicNotFoundException;
import com.takuba.exception.EditorialNotFoundException;
import com.takuba.exception.UsuarioNotFoundException;
import com.takuba.model.Calendario;
import com.takuba.model.Comic;
import com.takuba.model.Editorial;
import com.takuba.model.Periodicidad;
import com.takuba.model.Usuario;
import com.takuba.repository.CalendarioRepository;
import com.takuba.repository.ComicRepository;
import com.takuba.repository.EditorialRepository;
import com.takuba.repository.PeriodicidadRepository;
import com.takuba.repository.TodoRepositoryImpl;
import com.takuba.repository.UsuarioRepository;
import com.takuba.util.FechasUtil;

@RestController
public class ComicsController {
	@Autowired
	ComicRepository comicRepository;
	@Autowired
	EditorialRepository editorialRepository;
	@Autowired
	PeriodicidadRepository periodicidadRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CalendarioRepository calendarioRepository;
	
	@Autowired
	TodoRepositoryImpl todoRepository;  
	
	private static Logger log = LoggerFactory.getLogger(ComicsController.class);
	
	@GetMapping("/comic/todos")
	public List<Comic> listarTodos(){
		return comicRepository.findAll();
	}
	
	@GetMapping("/comic/titulo/{titulo}")
	public Comic buscarComicPorTitulo(@PathVariable String titulo){
		Comic resultado= comicRepository.findByTitulo(titulo);
		if(resultado == null){
			throw new ComicNotFoundException("El comic:"+titulo+" no existe");
		}
		return resultado;
	}
	@GetMapping("/comic/editorial/{editorial}")
	public List<Comic>buscarComicPorEditorial(@PathVariable String editorial){
		Editorial ed = editorialRepository.findByNombre(editorial);
		if(ed == null){
			throw new EditorialNotFoundException("La editorial "+editorial+" no esta dada de alta");
		}
		return comicRepository.findByEditorial(ed);
	}
	@GetMapping("/comic/periodicidad/{descripcion}")
	public List<Comic>buscarComicPorPeriodicidad(@PathVariable String descripcion){
		Periodicidad periodicidad = periodicidadRepository.findByDescripcion(descripcion);
		if(periodicidad == null){
			throw new EditorialNotFoundException("La editorial "+descripcion+" no esta dada de alta");
		}
		return comicRepository.findByPeriodicidad(periodicidad);
	}
	
	@GetMapping("/comic/usuario/{username}")
	public List<Comic> buscarComicsUsuario(@PathVariable String username){
		Usuario usu = usuarioRepository.findByUsuario(username);
		if(usu==null){
			throw new UsuarioNotFoundException("El usuario "+username+" no esta dado de alta");
		}
		List<Comic> lstComics=usu.getComics();
		log.info("Comics:"+lstComics.size());
		
		return lstComics;
	}
	@GetMapping("/comic/{username}")
	public Usuario buscarUsuario(@PathVariable String username){
		Usuario usu = usuarioRepository.findByUsuario(username);
		if(usu==null){
			throw new UsuarioNotFoundException("El usuario "+username+" no esta dado de alta");
		}
		return usu;
	}
	@PostMapping("/comic/")
	public ResponseEntity<Object> agregarCalendarioUsuario(@RequestBody Usuario usuario){
		log.info(usuario.toString());
		
		List<Calendario> lstCalendarios = usuario.getCalendarios();
		lstCalendarios.forEach(new Consumer<Calendario>() {

			@Override
			public void accept(Calendario cal) {
				Optional<Calendario> calOptional = calendarioRepository.findById(cal.getId());
				if(!calOptional.isPresent()){
					calendarioRepository.save(cal);
				}else{
					log.info("Existe el elemento {}", cal);
				}
				
			}
		});
		
		usuario.getComics().forEach(new Consumer<Comic>() {

			@Override
			public void accept(Comic com) {
				if(!comicRepository.findById(com.getIdComic()).isPresent()){
					comicRepository.save(com);
				}
				
			}
		});

		URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(usuario.getUsuario()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/comic/library/{userid}")
	public List<Comic> buscarComicsEnBiblioteca(@PathVariable Integer userid){
		Usuario usuario = usuarioRepository.findById(userid).orElseThrow(()->new UsuarioNotFoundException("El usuario no existe"));
		List<Comic> listaComics = comicRepository.findComicsInLibrary(userid);
		if(listaComics.isEmpty()){
			throw new BibliotecaNotFoundException("No existen comics en la biblioteca de "+usuario);
		}
		return listaComics;
	}
	
	@PostMapping("/comic/library/")
	public ResponseEntity<Object> guardarComicBiblioteca(@RequestBody Biblioteca biblioteca){
		comicRepository.findById(biblioteca.getIdComic()).orElseThrow(()->new ComicNotFoundException("El comic no existe"));
		Usuario usuario = usuarioRepository.findById(biblioteca.getIdUsuario()).orElseThrow(()->new UsuarioNotFoundException("El usuario con id: "+biblioteca.getIdUsuario()+" no existe"));
		todoRepository.guardarBiblioteca(biblioteca);
		URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(usuario.getIdUsuario()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/comic/calendario/{idusuario}")
	private List<Calendario> buscarCalendarioUsuario(@PathVariable Integer idusuario){
		List<Calendario> lstCalendario = calendarioRepository.buscarCalendario(idusuario);
		if(lstCalendario.isEmpty()){
			throw new CalendarioNotFoundException("No existe calendario para el usuario:"+idusuario);
		}
		return lstCalendario;
	}
	@PostMapping("/comic/calendario/")
	public ResponseEntity<Object>actualizarCalendario(@RequestBody Calendario calendario){
		Periodicidad periodicidad = periodicidadRepository.buscarPeriodicidad(calendario.getId().getIdComic());
		Comic comic = comicRepository.findById(calendario.getId().getIdComic()).orElseThrow(()->new ComicNotFoundException("El comic no existe"));
		calendario.setNumero(calendario.getNumero().intValue()+1);
		if(calendario.getNumero().compareTo(comic.getNumeroFinal()) < 1){
			Date fechaActualizada = FechasUtil.calcularFecha(calendario.getFechaPublicar(), periodicidad.getIdPeriodicidad());
			calendario.setFechaPublicar(fechaActualizada);
			calendarioRepository.save(calendario);
		}else{
			Biblioteca biblioteca = new Biblioteca();
			biblioteca.setIdComic(calendario.getId().getIdComic());
			biblioteca.setIdUsuario(calendario.getId().getIdUsuario());
			todoRepository.guardarBiblioteca(biblioteca);
			calendarioRepository.delete(calendario);
		}
		
		URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(calendario.getId().getIdUsuario()).toUri();
		return ResponseEntity.created(location).build();
	}
}
