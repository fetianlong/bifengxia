/**
 * Created by Administrator on 2016/9/16.
 */
$(function(){
	
    $("span.menu").click(function(){
        $(" ul.navig").slideToggle("slow" , function(){
        });
    });
    $('.navig').hover(function(){
        $('.xiala_opacity').css('display','block');
        $('.xiala').css('display','block');
    },function(){
        $('.xiala_opacity').css('display', 'none');
        $('.xiala').css('display', 'none');
     });
    $('.xiala_opacity').hover(function () {
        $('.xiala_opacity').css('display', 'block');
        $('.xiala').css('display', 'block');
    }, function () {
        $('.xiala_opacity').css('display', 'none');
        $('.xiala').css('display', 'none');
        return ;
    })
	$('ul.navig li').mouseover(function(ev){
		var target=ev.target;
		console.log(target);
		console.log(target==$('.xiala li'));
	})
    /*景点预定收起和展开*/
    $('.shensuo').click(function(){
        if($(this).parent().css('overflow')=='hidden'){
        	$('.yuding').addClass('shou')
        	$('.yuding .shensuo').html('详情 +');
            $(this).parent().removeClass('shou')
            $(this).html('收起 -');
            
        }else{
            $(this).parent().addClass('shou')
            $(this).html('详情 +')
        }
    })
    /*数量加减*/
    $('.jia').click(function(){
        $(this).prev().prev().css('color','#333');
        var val=parseFloat($(this).prev().val());
        $(this).prev().val(val+1);
        var count=parseFloat($(this).prev().val());
        var danjia=parseFloat($(this).parent().prev().find('span:first').html());
        $(this).parent().next().find('span:first').html(count*danjia);
        $("#priceCount").val(count);
        $('.sum').html(count*danjia)
    })
    $('.jian').click(function(){
        var val=parseFloat($(this).next().val());
        if(val>1){
            $(this).next().val(val-1);
        }else{
            return;
        }
        if(val==2){
            $(this).css('color','#cecece');
        }
        var count=parseFloat($(this).next().val());
        var danjia=parseFloat($(this).parent().prev().find('span:first').html());
        $(this).parent().next().find('span:first').html(count*danjia);
        $("#priceCount").val(count);
        $('.sum').html(count*danjia)
    })
    $('.kind').click(function(){
        $('.kind').removeClass('active');
        $(this).addClass('active')
    })
    $('.syz').click(function(){
        var hi=$('.tup_all').height();
        var top=parseInt($('.tup_list_wrap').css('top'));
        var hi_all=$('.tup_list_wrap').height();

        if(top==0){
            $(this).css('color','#ccc')
            $('.xyz').css('color','#fff')
        }else{
            $('.tup_list_wrap').css('top',top+hi+'px');
        }

    })
    $('.xyz').click(function(){
        var hi=$('.tup_all').height();
        var top=parseInt($('.tup_list_wrap').css('top'));
        var hi_all=$('.tup_list_wrap').height();

        if(-top<hi_all-hi-50){
            $('.tup_list_wrap').css('top',top-hi+'px');
        }else{
            $(this).css('color','#ccc');
            $('.syz').css('color','#fff');

        }
    })
    $('.syt').click(function(){
        var index=$('.tup_l_wrap img.active').index();
        console.log(index)
        if(index>0){
            index-=1;
            $('.tup_l_wrap img').removeClass('active')
            $('.tup_l_wrap img').eq(index).addClass('active');
        }
        $('.gongji').html($('.tup_l img').length);
        $('.diji').html(index+1);
    })
    $('.gongji').html($('.tup_l img').length);
    $('.xyt').click(function(){
        var index=$('.tup_l_wrap img.active').index();
        var length=$('.tup_l_wrap img').length;
        console.log(index)
        console.log(length)
        if(index!=length-1){
            index+=1;
            $('.tup_l_wrap img').removeClass('active')
            $('.tup_l_wrap img').eq(index).addClass('active');
        }
        $('.gongji').html($('.tup_l img').length);
        $('.diji').html(index+1);
    })
    $('.tup_list').click(function(){
    	var index=$(this).index();
    	$('.items').removeClass('active');
    	$('.items').eq(index).addClass('active');
    	var length=$(this).find('img').length;
    	$('.diji').html(1);
    	$('.gongji').html(length);
        $('.tup_l_wrap').html($(this).html());
    })
    //选项卡
     $('.tab').click(function(){
        $('.tab').removeClass('active');
        $(this).addClass('active');
        var index=$(this).index();
        $('.tabitem').removeClass('active');
        $('.tabitem').eq(index).addClass('active');
        if(index==0){
            $('.bor_gr span').html('个人中心')
        }else{
            $('.bor_gr span').html('历史订单')
        }
    })
//导览图弹框
    $('.daolantu_text a').click(function(){
    	layer.open({
            type: 1,
            title: false,
            closeBtn: 2,
            area: '420px',
            skin: 'layui-layer-nobg standard', //没有背景色
            /*shadeClose: true,*/
            content: $('.daolantu_tc')
        });
    })
    /*
    $('.more').click(function(){
    	if($('.shengming').css('display')=='block'){
    		$('.shengming').css('display','none')
    		$(this).html('更多 +');
    	}else{
    		$('.shengming').css('display','block')
    		$(this).html('收起更多 -');
    	}
    })
    */
})
