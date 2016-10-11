function page(pageId,pageCount,pageIndex){
    $("#excit").html("");
    var paging=
            "<ul id='table-pagination'>" +
                 "<li id='prevBtn' class='prev disabled'><a>«上一页</a></li>"+
                 "<li class='pageBtn active' data-num='1'><a>"+(pageIndex+1)+"</a></li>"+
                 "<li id='nextBtn' class='next disabled'><a>下一页»</a></li>"+
            "</ul>"+
            "<div>" +
                "<span>共"+pageCount+"页&nbsp;</span>"+
                " <span>到"+
                "<input id='selectPageNum' type='text' class='page-num'>"+
                 "<button id='selectPage' class='page-confirm'>确定</button>页"+
                 "</span>"+
            "</div>"
    console.log(pageIndex);
    $("#"+pageId+"").append(paging);
}
