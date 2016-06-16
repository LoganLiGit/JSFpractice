	// 當網頁載入完
	$(window).load(function(){
		var $win = $(window),
			$ad = $('#abgne_float_ad').css('opacity', 1).show(),	// 讓廣告區塊變透明且顯示出來
			_width = $ad.width(),
			_height = $ad.height(),
			_diffY = 0, _diffX = 0,	// 距離右及下方邊距
			_moveSpeed = 80;	// 移動的速度
		
		// 先把 #abgne_float_ad 移動到定點
		$ad.css({
			top: $(document).height(),
			left: $win.width() - _width - _diffX,
			opacity: 1
		});

		// 幫網頁加上 scroll 及 resize 事件
		$win.bind('scroll resize', function(){
			var $this = $(this);
			
			// 控制 #abgne_float_ad 的移動
			$ad.stop().animate({
				top: $this.scrollTop() + $this.height() - _height - _diffY,
				left: $this.scrollLeft() + $this.width() - _width - _diffX
			}, _moveSpeed);
		}).scroll();	// 觸發一次 scroll()
	});
	
	//Div遮罩
    function ShowDIV(thisObjID) {
        $("#BgDiv").css({ display: "block", height: $(document).height() });
        var yscroll = document.documentElement.scrollTop;
        $("#" + thisObjID).css("top", "50%");
        $("#" + thisObjID).css("display", "block");
        document.documentElement.scrollTop = 0;
    }

    function closeDiv(thisObjID) {
        $("#BgDiv").css("display", "none");
        $("#" + thisObjID).css("display", "none");
    }