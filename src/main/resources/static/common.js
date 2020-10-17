/**********
 *
 * Please, go to the bottom part of the page to see the skeleton effect :)
 *
 **********/
$(function () {
	$('[data-toggle="tooltip"]').tooltip();
});



function fnSearch() {
	var searchText = $('#searchText').val();
	window.location.href = '/search/' + searchText + "/1";
}
