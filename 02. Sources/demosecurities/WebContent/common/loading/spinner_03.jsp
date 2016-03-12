<style>
.spinner_03-loader {
	position: relative;
	left: calc(50% - 13px);
	width: 26px;
	height: 26px;
	border-radius: 50%;
		-o-border-radius: 50%;
		-ms-border-radius: 50%;
		-webkit-border-radius: 50%;
		-moz-border-radius: 50%;
	perspective: 320px;
    float: left;
    top: 5px;
    left: 0px;
}

.spinner_03-inner {
	position: absolute;
	width: 100%;
	height: 100%;
	box-sizing: border-box;
		-o-box-sizing: border-box;
		-ms-box-sizing: border-box;
		-webkit-box-sizing: border-box;
		-moz-box-sizing: border-box;
	border-radius: 50%;
		-o-border-radius: 50%;
		-ms-border-radius: 50%;
		-webkit-border-radius: 50%;
		-moz-border-radius: 50%;	
}

.spinner_03-inner.spinner_03-one {
	left: 0%;
	top: 0%;
	animation: spinner_03-rotate-one 1.15s linear infinite;
		-o-animation: spinner_03-rotate-one 1.15s linear infinite;
		-ms-animation: spinner_03-rotate-one 1.15s linear infinite;
		-webkit-animation: spinner_03-rotate-one 1.15s linear infinite;
		-moz-animation: spinner_03-rotate-one 1.15s linear infinite;
	border-bottom: 1px solid rgb(69,182,176);
}

.spinner_03-inner.spinner_03-two {
	right: 0%;
	top: 0%;
	animation: spinner_03-rotate-two 1.15s linear infinite;
		-o-animation: spinner_03-rotate-two 1.15s linear infinite;
		-ms-animation: spinner_03-rotate-two 1.15s linear infinite;
		-webkit-animation: spinner_03-rotate-two 1.15s linear infinite;
		-moz-animation: spinner_03-rotate-two 1.15s linear infinite;
	border-right: 1px solid rgb(69,182,176);
}

.spinner_03-inner.spinner_03-three {
	right: 0%;
	bottom: 0%;
	animation: spinner_03-rotate-three 1.15s linear infinite;
		-o-animation: spinner_03-rotate-three 1.15s linear infinite;
		-ms-animation: spinner_03-rotate-three 1.15s linear infinite;
		-webkit-animation: spinner_03-rotate-three 1.15s linear infinite;
		-moz-animation: spinner_03-rotate-three 1.15s linear infinite;
	border-top: 1px solid rgb(69,182,176);
}







@keyframes spinner_03-rotate-one {
	0% {
		transform: rotateX(35deg) rotateY(-45deg) rotateZ(0deg);
	}
	100% {
		transform: rotateX(35deg) rotateY(-45deg) rotateZ(360deg);
	}
}

@-o-keyframes spinner_03-rotate-one {
	0% {
		-o-transform: rotateX(35deg) rotateY(-45deg) rotateZ(0deg);
	}
	100% {
		-o-transform: rotateX(35deg) rotateY(-45deg) rotateZ(360deg);
	}
}

@-ms-keyframes spinner_03-rotate-one {
	0% {
		-ms-transform: rotateX(35deg) rotateY(-45deg) rotateZ(0deg);
	}
	100% {
		-ms-transform: rotateX(35deg) rotateY(-45deg) rotateZ(360deg);
	}
}

@-webkit-keyframes spinner_03-rotate-one {
	0% {
		-webkit-transform: rotateX(35deg) rotateY(-45deg) rotateZ(0deg);
	}
	100% {
		-webkit-transform: rotateX(35deg) rotateY(-45deg) rotateZ(360deg);
	}
}

@-moz-keyframes spinner_03-rotate-one {
	0% {
		-moz-transform: rotateX(35deg) rotateY(-45deg) rotateZ(0deg);
	}
	100% {
		-moz-transform: rotateX(35deg) rotateY(-45deg) rotateZ(360deg);
	}
}

@keyframes spinner_03-rotate-two {
	0% {
		transform: rotateX(50deg) rotateY(10deg) rotateZ(0deg);
	}
	100% {
		transform: rotateX(50deg) rotateY(10deg) rotateZ(360deg);
	}
}

@-o-keyframes spinner_03-rotate-two {
	0% {
		-o-transform: rotateX(50deg) rotateY(10deg) rotateZ(0deg);
	}
	100% {
		-o-transform: rotateX(50deg) rotateY(10deg) rotateZ(360deg);
	}
}

@-ms-keyframes spinner_03-rotate-two {
	0% {
		-ms-transform: rotateX(50deg) rotateY(10deg) rotateZ(0deg);
	}
	100% {
		-ms-transform: rotateX(50deg) rotateY(10deg) rotateZ(360deg);
	}
}

@-webkit-keyframes spinner_03-rotate-two {
	0% {
		-webkit-transform: rotateX(50deg) rotateY(10deg) rotateZ(0deg);
	}
	100% {
		-webkit-transform: rotateX(50deg) rotateY(10deg) rotateZ(360deg);
	}
}

@-moz-keyframes spinner_03-rotate-two {
	0% {
		-moz-transform: rotateX(50deg) rotateY(10deg) rotateZ(0deg);
	}
	100% {
		-moz-transform: rotateX(50deg) rotateY(10deg) rotateZ(360deg);
	}
}

@keyframes spinner_03-rotate-three {
	0% {
		transform: rotateX(35deg) rotateY(55deg) rotateZ(0deg);
	}
	100% {
		transform: rotateX(35deg) rotateY(55deg) rotateZ(360deg);
	}
}

@-o-keyframes spinner_03-rotate-three {
	0% {
		-o-transform: rotateX(35deg) rotateY(55deg) rotateZ(0deg);
	}
	100% {
		-o-transform: rotateX(35deg) rotateY(55deg) rotateZ(360deg);
	}
}

@-ms-keyframes spinner_03-rotate-three {
	0% {
		-ms-transform: rotateX(35deg) rotateY(55deg) rotateZ(0deg);
	}
	100% {
		-ms-transform: rotateX(35deg) rotateY(55deg) rotateZ(360deg);
	}
}

@-webkit-keyframes spinner_03-rotate-three {
	0% {
		-webkit-transform: rotateX(35deg) rotateY(55deg) rotateZ(0deg);
	}
	100% {
		-webkit-transform: rotateX(35deg) rotateY(55deg) rotateZ(360deg);
	}
}

@-moz-keyframes spinner_03-rotate-three {
	0% {
		-moz-transform: rotateX(35deg) rotateY(55deg) rotateZ(0deg);
	}
	100% {
		-moz-transform: rotateX(35deg) rotateY(55deg) rotateZ(360deg);
	}
}
.spinner_03-legend{
    margin-left: 30px;
    display: inline;
    line-height: 26px;
    font-size: 11px;
    color: rgb(69,182,176);
}
</style>

<div class="spinner_03-loader">
	<div class="spinner_03-inner spinner_03-one"></div>
	<div class="spinner_03-inner spinner_03-two"></div>
	<div class="spinner_03-inner spinner_03-three"></div>
	<span class="spinner_03-legend">Processing...</span>
</div>