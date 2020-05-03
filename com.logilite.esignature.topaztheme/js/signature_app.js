/*
 * Design signature board for work with ZK
 *  
 * @author: Sachin Bhimani  (sachin.bhimani89@gmail.com)
 * 
 * @reference: https://github.com/szimek/signature_pad
 */
function SignatureApp() {

	this.initSignaturePad = initSignaturePad;

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
		canvas.style = 'margin: 10px; padding: 5px; background: #f1f1f1; border: 1px solid #000; border-radius: 5px; width:450px';
		imagePanel.parentNode.insertBefore(canvas, imagePanel);
		
		var tmr;
		var ctx = canvas.getContext('2d');
		SetTabletState(0, tmr);
		SetJustifyMode(0);
		SetDisplayPenWidth(4);
		SetEncryptionMode(2);
		SetKeyString("00000000000000"); 
		SetSigCompressionMode(1);
		ClearTablet();
		if (tmr == null) {
			tmr = SetTabletState(1, ctx, 50);
		} else {
			SetTabletState(0, tmr);
			tmr = null;
			tmr = SetTabletState(1, ctx, 50);
		}

		// Save Signature as PNG image
		saveBtn.addEventListener('click', function(event) {
			var imgData = null;
			if (NumberOfTabletPoints() != 0) {
				imgData = canvas.toDataURL('image/png');
				if (canvas.width == 1 && canvas.height == 1)
					imgData = null;
			}
			var widget = zk.Widget.$(currFormID);
			var event = new zk.Event(widget, 'onSignatureEvent', {
				data : [ imgData ]
			}, {
				toServer : true
			});
			zAu.send(event);
			ClearTablet();
			SetTabletState(0, tmr);
		});

		// Clear signature data
		resetBtn.addEventListener('click', function(event) {
			ClearTablet();
		});

		// Remove registered event on signature pad
		cancelBtn.addEventListener('click', function(event) {
			ClearTablet();
			SetTabletState(0, tmr);
		});
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
