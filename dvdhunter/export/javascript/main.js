$(init);

function init()
{

	// this function will insert in page the updated list of items
	/*function updateListOfItems(newList)
	{
		$("#ItemsList").empty();
		$("#ItemsList").append($("option",newList).fadeIn("fast"));
		$("#ItemsList").fadeIn("def");
	}*/

	// this function will insert in page the updated item content	
	function updateItemInformations(infoToInsert)
	{
		$("#content").empty();
		$("#content").fadeIn("def");
		$("#content").append(infoToInsert);	
		
		
		// handler of click on cover image to obtain zoom-in and out animation
		var zoom = false;	
		
		$("#itemCover").css({'cursor': 'pointer'});
		$("#itemCover").click(function()
		{
			
			var cover = $("#itemCover");
			
			if(!zoom)
				cover.animate({maxWidth:"50%"}, 700);
			else
			{
				cover.animate({maxWidth:"20%"}, 700, "swing",
				 function () { cover.css('maxWidth',null);});
				
			}
			zoom = !zoom;
			
		});
	}
	
	var selectedItemInList = $("#ItemsList");
		
			
	//this function will create ajax request to load data of item from server
	function reloadItemInfo()
	{
		if(selectedItemInList.val() != null)
		{
			var url = "items/" + selectedItemInList.val() + ".html";
			$.get(url,updateItemInformations,true);
			$("#content").hide();
		}
	}
	
	selectedItemInList.change(reloadItemInfo);
}