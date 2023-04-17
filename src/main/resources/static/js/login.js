(function(){
    var n,h,j,d,g,f,m,c=true;
    i();
    a();
    function i(){
        n=window.innerWidth;
        h=window.innerHeight;
        m={x:0,y:h};
        j=document.getElementById("large-header");
        j.style.height=h+"px";
        d=document.getElementById("demo-canvas");
        d.width=n;
        d.height=h;
        g=d.getContext("2d");
        f=[];
        for(var p=0;p<n*0.5;p++){
            var o=new e();
            f.push(o)
        }
        b()
    }
    function a(){
        window.addEventListener("scroll",l);
        window.addEventListener("resize",k)
    }
    function l(){
        if(document.body.scrollTop>h){
            c=false
        }else{
            c=true
        }
    }
    function k(){
        n=window.innerWidth;
        h=window.innerHeight;
        j.style.height=h+"px";
        d.width=n;
        d.height=h
    }
    function b(){
        if(c){
            g.clearRect(0,0,n,h);
            for(var o in f){
                f[o].draw()
            }
        }requestAnimationFrame(b)
    }
    function e(){
        var o=this;
        (function(){o.pos={};p();console.log(o)})();
        function p(){
            o.pos.x=Math.random()*n;
            o.pos.y=h+Math.random()*100;
            o.alpha=0.1+Math.random()*0.3;
            o.scale=0.1+Math.random()*0.3;
            o.velocity=Math.random()
        }
        this.draw=function(){
            if(o.alpha<=0){
                p()
            }
            o.pos.y-=o.velocity;
            o.alpha-=0.0005;
            g.beginPath();
            g.arc(o.pos.x,o.pos.y,o.scale*10,0,2*Math.PI,false);
            g.fillStyle="rgba(255,255,255,"+o.alpha+")";
            g.fill()
        }
    }
})();