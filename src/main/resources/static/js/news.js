var $news = {};

(function($news){

    
    $news.initForm = function(){
        // btn link 공통 스타일
        $('.btn.news-btn-link').click(function(e){
            var $e = $(e.target);
            var href = $e.attr('data-href');
            location.href = href;
        });
    }


})($news);