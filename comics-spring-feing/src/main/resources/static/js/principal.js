var arrEvent = [];
	$(document).ready(function() {
		$('#tblcomics tr').click(function() {
			var tableData = $(this).children('td').map(function () {
                return $(this).text();
            }).get();
	        $('#textTitulo').attr('value',tableData[0]);
	        $('#textPrecio').attr('value',tableData[1]);
	        $('#txtNumero').attr('disabled',false);
	        $('#datepicker').attr('disabled',false);
	        $('#btnAgregar').attr('disabled',false);
	    });
		$( "#datepicker" ).datepicker();
		$('#external-events .fc-event').each(function() {

			// store data so the calendar knows to render an event upon drop
			$(this).data('event', {
				title : $.trim($(this).text()), // use the element's text as the event title
				stick : true, // maintain when user navigates (see docs on the renderEvent method)
				id : $(this).id
			});

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
			//  original position after the drag
			});

		});

		$('#calendar').fullCalendar({
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,listWeek'
			},
			// defaultDate: '2018-03-12',
			editable : true,
			droppable : true, // this allows things to be dropped onto the calendar
			eventDrop : function(event, delta, revertFunc) {
				$.post("update-calendar", {
					idUsuario : $('input[type=hidden]').val(),
					fecha : event.start.format(),
					idComic : event.id
				}, function(data, status) {
					alert("Data: " + data + "\nStatus: " + status);
				});
			},
			navLinks : true, // can click day/week names to navigate views
			eventLimit : true, // allow "more" link when too many events
			navLinkDayClick : function(date, jsEvent) {
				console.log('day', date.format()); // date is a moment
				console.log('coords', jsEvent.pageX, jsEvent.pageY);
			},
			eventClick : function(calEvent, jsEvent, view) {
				// change the border color just for fun
				$(this).css('border-color', 'red');
				$(this).css('background-color', 'black');
				var banderaAgregar = true;
				if (arrEvent.length > 0) {
					for (i = 0; i < arrEvent.length; i++)
						var tmp = arrEvent[i]
					if (tmp.id == calEvent.id) {
						banderaAgregar = false;
					}
				}
				if (banderaAgregar) {
					arrEvent.push(calEvent);
				}
			},
			events : {
				url : '/get-events',
				type : 'POST',
				error : function() {
					$('#script-warning').show();
				},
				data : function() {
					return {
						userid : $('#userId').val()
					};
				}
			},
			loading : function(bool) {
				$('#loading').toggle(bool);
			}
		});

	});
	function enviar(accion, mensaje) {
		var numElementos = arrEvent.length;
		if (numElementos > 0) {
			var r = confirm("¿" + mensaje + " Comics?");
			if (r) {
				var url = "/" + accion + "?";
				for (i = 0; i < numElementos; i++) {
					url += "idcomic=" + arrEvent[i].id + "&"
				}
				url = url.substring(0, url.length - 1);
				$.get(url, function(data, status) {
					var total = arrEvent.length;
					if (total > 0) {
						for (i = 0; i < total; i++) {
							var tmp = arrEvent.pop();
							$('#calendar')
									.fullCalendar('removeEvents', tmp._id);
						}
					}
					alert(data.mensaje);
				});
			}
		} else {
			alert("No hay comics seleccionados");
		}
	}
	function agregarComicCalendario(){
		console.log("idUsuario:"+$('input[type=hidden]').val());
		console.log("fecha :" +$("#datepicker").val());
		console.log("titulo :" +$("#textTitulo").val());
		console.log("txtNumero :" +$("#txtNumero").val());
		$.post("add-calendar", {
			idUsuario : $('input[type=hidden]').val(),
			fecha : $("#datepicker").val(),
			titulo : $("#textTitulo").val(),
			numero:$("#txtNumero").val()
		}, function(data, status) {
			$('#calendar').fullCalendar( 'refetchEvents' );
			alert("Data: " + data + "\nStatus: " + status);
		});
	}