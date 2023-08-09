/*
 * Design signature board for work with ZK
 * 
 * @author: Deepak Pansheriya
 * @author: Sachin Bhimani
 * @author: Vipul Vaghela
 * @author: Prakash Dudhat
 * 
 * @reference: https://github.com/szimek/signature_pad
 */
function SignatureApp() {

	this.initSignaturePad = initSignaturePad;
	this.trimCanvas = trimCanvas;

	// create a Signature Pad
	function initSignaturePad(imgID, parentID, currFormID, resetBtnID,
			saveBtnID, cancelBtnID) {

		var currForm = document.getElementById(currFormID);
		var saveBtn = document.getElementById(saveBtnID);
		var resetBtn = document.getElementById(resetBtnID);
		var cancelBtn = document.getElementById(cancelBtnID);

		var centerPanel = document.getElementById(parentID);
		var imagePanel = document.getElementById(imgID);
		imagePanel.style.display = "none";
		imagePanel.src = "";

		var canvas = document.createElement('canvas');
		canvas.id = 'signature-pad';
		canvas.style = 'margin: 8px; padding: 1px; background: #f1f1f1; border: 1px solid #000; border-radius: 5px;';
		imagePanel.parentNode.insertBefore(canvas, imagePanel);

		// Adjust canvas coordinate space taking into account pixel ratio,
		// to make it look crisp on mobile devices. This also causes canvas
		// to be cleared.
		function resizeCanvas() {
			// When zoomed out to less than 100%, for some very strange reason,
			// some browsers report devicePixelRatio as less than 1 and only
			// part of the canvas is cleared then.

			// TODO Signature is resize based on the ratio value, implement code
			// to resize based on the canvas form size.
			// var ratio = Math.max(window.devicePixelRatio || 1, 1);
			const sdata = signaturePad.toDataURL('image/png');
			canvas.width = (currForm.offsetWidth - 25);
			canvas.height = (currForm.offsetHeight - 105);
			canvas.getContext("2d").scale(1, 1);
			signaturePad.fromDataURL(sdata);
		}

		signaturePad = new SignaturePad(canvas, {
			backgroundColor : 'rgba(255, 255, 255, 255)',
			penColor : 'rgb(0, 0, 0)'
		});

		resizeCanvas();

		var tmr;
		var isSignWebTabletEnable = true;

		try {
			SetTabletState(0, tmr);
			SetJustifyMode(0);
			SetDisplayPenWidth(8);
			SetEncryptionMode(2);
			SetKeyString("00000000000000");
			SetSigCompressionMode(1);
			ClearTablet();

			var ctx = canvas.getContext('2d');
			if (tmr == null) {
				tmr = SetTabletState(1, ctx, 50);
			} else {
				SetTabletState(0, tmr);
				tmr = null;
				tmr = SetTabletState(1, ctx, 50);
			}
		} catch (err) {
			console.log('Error sign in tablet ' + err.name);
			isSignWebTabletEnable = false;
		}

		// Save Signature as PNG image
		saveBtn.addEventListener('click', function(event) {
			var cnvs = trimCanvas(signaturePad._canvas);
			var imgData = cnvs.toDataURL('image/png');
			if (cnvs.width == 1 && cnvs.height == 1)
				imgData = null;

			var widget = zk.Widget.$(currFormID);
			var event = new zk.Event(widget, 'onSignatureEvent', { data : [ imgData ] }, {toServer : true});
			zAu.send(event);

			if (isSignWebTabletEnable) {
				closingSigWeb();
			}
		});

		// Clear signature data
		resetBtn.addEventListener('click', function(event) {
			signaturePad.clear();

			if (isSignWebTabletEnable) {
				ClearTablet();
			}
		});

		// Remove registered event on signature pad
		cancelBtn.addEventListener('click', function(event) {
			if (signaturePad != undefined)
				signaturePad.off();
			signaturePad = undefined;

			if (isSignWebTabletEnable) {
				closingSigWeb();
			}
		});

	}

	// trim the canvas before the save sign image
	function trimCanvas(canvas) {
		var context = canvas.getContext('2d')
		var imgWidth = canvas.width
		var imgHeight = canvas.height
		var imgData = context.getImageData(0, 0, imgWidth, imgHeight).data

		// get the corners of the relevant content (everything that's not white)
		var cropTop = scanY(true, imgWidth, imgHeight, imgData)
		var cropBottom = scanY(false, imgWidth, imgHeight, imgData)
		var cropLeft = scanX(true, imgWidth, imgHeight, imgData)
		var cropRight = scanX(false, imgWidth, imgHeight, imgData)

		// + 1 is needed because this is a difference, there are n + 1 pixels in
		// between the two numbers inclusive
		var cropXDiff = (cropRight - cropLeft) + 1
		var cropYDiff = (cropBottom - cropTop) + 1

		// get the relevant data from the calculated coordinates
		var trimmedData = context.getImageData(cropLeft, cropTop, cropXDiff,
				cropYDiff)

		// set the trimmed width and height
		canvas.width = cropXDiff
		canvas.height = cropYDiff
		// clear the canvas
		context.clearRect(0, 0, cropXDiff, cropYDiff)
		// place the trimmed data into the cleared canvas to create a new,
		// trimmed canvas
		context.putImageData(trimmedData, 0, 0)
		return canvas // for chaining
	}

	// returns the RGBA values of an x, y coord of imgData
	function getRGBA(x, y, imgWidth, imgData) {
		return {
			red : imgData[(imgWidth * y + x) * 4],
			green : imgData[(imgWidth * y + x) * 4 + 1],
			blue : imgData[(imgWidth * y + x) * 4 + 2],
			alpha : imgData[(imgWidth * y + x) * 4 + 3]
		}
	}

	function getAlpha(x, y, imgWidth, imgData) {
		return getRGBA(x, y, imgWidth, imgData).alpha
	}

	// finds the first y coord in imgData that is not white
	function scanY(fromTop, imgWidth, imgHeight, imgData) {
		var offset = fromTop ? 1 : -1
		var firstCol = fromTop ? 0 : imgHeight - 1

		// loop through each row
		for (var y = firstCol; fromTop ? (y < imgHeight) : (y > -1); y += offset) {
			// loop through each column
			for (var x = 0; x < imgWidth; x++) {
				// if not white, return column
				if (getAlpha(x, y, imgWidth, imgData)) {
					return y
				}
			}
		}
		// the whole image is white already
		return null
	}

	// finds the first x coord in imgData that is not white
	function scanX(fromLeft, imgWidth, imgHeight, imgData) {
		var offset = fromLeft ? 1 : -1
		var firstRow = fromLeft ? 0 : imgWidth - 1

		// loop through each column
		for (var x = firstRow; fromLeft ? (x < imgWidth) : (x > -1); x += offset) {
			// loop through each row
			for (var y = 0; y < imgHeight; y++) {
				// if not white, return row
				if (getAlpha(x, y, imgWidth, imgData)) {
					return x
				}
			}
		}
		// the whole image is white already
		return null
	}
}

var signatureApp = new SignatureApp();

window.onunload = window.onbeforeunload = (function() {
	closingSigWeb()
})

function closingSigWeb() {
	ClearTablet();
	SetTabletState(0, tmr);
}
