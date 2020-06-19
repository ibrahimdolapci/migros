var app = require('express')();
var bodyParser = require('body-parser');
const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp();

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(bodyParser.raw());

// Take the text parameter passed to this HTTP endpoint and insert it into
// Cloud Firestore under the path /messages/:documentId/original
app.post('/',async (request, response) => {
    const {phone, text} = request.body;
    if(phone == "SanaIMarket"){
       io.emit('verification-code', text.split(" ")[0]);
    }

    // Grab the text parameter.
    const original = req.query.text;
    // Push the new message into Cloud Firestore using the Firebase Admin SDK.
    const writeResult = await admin.database('messages').add({original: original});
    // Send back a message that we've succesfully written the message
    response.status(200).send("OK")
});

exports.widgets = functions.https.onRequest(app);
