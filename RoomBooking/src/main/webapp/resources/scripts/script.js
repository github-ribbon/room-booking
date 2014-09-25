
$(document).ready(function()
{	
	$("a.shine").hover(function()
	{		
			$("a.shine:hover").fadeOut(1);
			$("a.shine:hover").fadeIn(700);
	},function()
	{		
		$("a.shine:hover").fadeIn();		
	});	
});