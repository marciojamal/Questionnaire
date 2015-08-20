$(function() {
	
	if ($('input.cnpj')[0]) {
		$("input.cnpj").mask("99.999.999/9999-99");  
	}
	
	if ($('input.date')[0]) {
		$("input.date").mask("99/99/9999");     
	}
	
	if ($('input.phone')[0]) {
		$("input.phone").mask("(99) 9999?9-9999");     
	}
	
	if ($('input.zip_code')[0]) {
		$("input.zip_code").mask("99999-999");  
	}
	
});