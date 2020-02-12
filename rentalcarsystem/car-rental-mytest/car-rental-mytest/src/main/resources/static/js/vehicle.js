
$("#tbUser").on('click', '.btnDelete', function () {
    $(this).closest('tr').remove();
});
/*
*//**
 *//*
$('.delete-btn').click(function() {
	var id = $(this).attr("data-vID");
	$.ajax({
		type : POST,
		url : 'delete',
		data : "vehicleId=" + id,
		success : function(data) {
			$(id).remove();
		}
	})
	$.ajax(function() {
		
		$('#deltedMessage').val(id + " is deleted.");
	});
});
$('.row-remover').on('click', function() {
    $(this).closest('tr').remove();
})

*/

$(".delete-btn").click(function(){
    $(this).parents('tr').first().remove();
});

$('.span.glyphicon-trash').live().on('click', function() {
    $(this).closest('tr').remove();
});