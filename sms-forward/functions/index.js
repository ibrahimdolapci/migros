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
app.post('/', async (request, response) => {
    const {phone, text} = request.body;

    // Grab the verification code.
    if (phone === "SanaIMarket") {
        const verificationCode = text.split(" ")[0];
        // Push the new message into Cloud Database using the Firebase Admin SDK.
        await admin.database().ref('verification-code').set(verificationCode);
    }

    // Send back a message that we've succesfully written the message
    response.status(200).send("OK")
});

exports.getMessage = functions.https.onRequest(app);
