PearsonDictionaryAndroid
========================

Sample Android App for connecting to Pearson Plug and Play - Longman Dictionary.

See http://developer.pearson.com for more information and to request an API key. 

Put your API key in the API_KEY variable in the Settings class, and run the application.

Note that currently the word and definition JSON can vary from response to response. In some cases, the SENSE key is missing from the JSON, I've enquired about the format of the returned data. If the app can't find the expected definition, it asks the user to try again.