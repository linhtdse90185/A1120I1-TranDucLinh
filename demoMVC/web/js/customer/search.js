$(document).ready(function() {
    $('#search').click(function(event) {
        let customerName = $('#customerName').val();
        $.post('search', {
            name : customerName
        }, function(responseText) {
            $('#result').html(responseText);
        });
    });
});