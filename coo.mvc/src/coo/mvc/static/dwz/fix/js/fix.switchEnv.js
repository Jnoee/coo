/** 覆盖DWZ的switchEnv函数 */
(function($){
    $.fn.switchEnv = function(){
        return this.each(function(){
            var $this = $(this);
            $this.click(function(){
                if ($this.hasClass("selected")) {
                    _hide($this);
                }
                else {
                    _show($this);
                }
                return false;
            });
            // 由于父容器的click返回false，这里需要设置弹出层中的link点击时阻止事件冒泡，使链接可以被激活。
            $this.find(">ul>li>a").click(function(event){
                event.stopPropagation();
            });
        });
    }
    function _show($box){
        $box.addClass("selected");
        $(document).bind("click", {
            box: $box
        }, _handler);
    }
    
    function _hide($box){
        $box.removeClass("selected");
        $(document).unbind("click", _handler);
    }
    
    function _handler(event){
        _hide(event.data.box);
    }
})(jQuery);