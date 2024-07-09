function Editor( {name}
) {
return (
<div>
<h1>Hello, {name}</h1>
<hr/>
<div id="toolstatus"></div>
<hr/>
<div id="container">
<BBCanvas />
</div>
<hr/>
<div id="info"></div>
</div>
);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
<Editor name="Daniel"/>
);


function BBCanvas() {
const [svrStatus, setSvrStatus] = React.useState({loadingState: 'Loading Canvas...'});
const myp5 = React.useRef(null);
const sketch = function (p) {
let x = 100;
let y = 100;
p.setup = function () {
p.createCanvas(700, 410);
}
p.draw = function () {
if (p.mouseIsPressed === true) {
p.fill(0, 0, 0);
p.ellipse(p.mouseX, p.mouseY, 20, 20);
}
if (p.mouseIsPressed === false) {
p.fill(255, 255, 255);
}
}
};
React.useEffect(() => {
myp5.current = new p5(sketch, 'container');
setSvrStatus({loadingState: 'Canvas Loaded'});
}, []);
return(
    <div>
    <h4>Drawing status: {svrStatus.loadingState}</h4>
    </div>);
}