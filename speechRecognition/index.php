<a>Click anywhere on screen to start speech recognition. </a>
<style>
a{
display:block;
}

</style>
<script
  src="https://code.jquery.com/jquery-3.2.1.js"
  integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  crossorigin="anonymous"></script>
<script>
var SpeechRecognition = SpeechRecognition || webkitSpeechRecognition;
var SpeechGrammarList = SpeechGrammarList || webkitSpeechGrammarList;
var SpeechRecognitionEvent = SpeechRecognitionEvent || webkitSpeechRecognitionEvent;

var recognition = new SpeechRecognition();
recognition.lang = 'en-US';
recognition.interimResults = false;
recognition.maxAlternatives = 1;
recognition.continuous=true;

document.body.onclick = function() {
  recognition.start();
console.log('Ready to receive command.');
}


recognition.onresult = function(event) {
  var last = event.results.length - 1;
  //addToTop(event.results[last][0].transcript);
  addToTop('Confidence: ' + event.results[last][0].confidence);
  var splitUpTranscript=(event.results[last][0].transcript).split(" ");
  for(var i=0;i<splitUpTranscript.length;i++){
	  addToTop(splitUpTranscript[i]);
	  sendJavaWord(splitUpTranscript[i]);
	}
}

recognition.onspeechend = function() {
}

recognition.onerror = function(event) {
 console.log('Error occurred in recognition: ' + event.error);
}

recognition.onend = function() { 
recognition.stop();
recognition.start();
 };
 function addToTop(string){
 document.body.innerHTML='<a>'+string+'</a>'+document.body.innerHTML;
 }
 function sendJavaWord(wordToSend){
	 $.ajax({ url: 'phpStuff/sendToJava.php',
         data: {word: wordToSend},
         type: 'post',
         success: function(output) {
                      //addToTop(output);
                  }
});
}
</script>
