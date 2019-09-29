// camel-k:
//
// kamel run --dev --profile=openshift --secret=telegram src/main/groovy/rest-telegram.groovy --trait=knative-service.autoscaling-target=1 --trait=knative-service.max-scale=10
//

from('undertow:http://0.0.0.0:8080/telegram')
    .convertBodyTo(String)
    .throttle(1)
    .to('telegram:bots?chatId={{telegram.chat.id}}')
    .transform()
        .groovy('request.body.message.text')
    .setHeader(org.apache.camel.Exchange.CONTENT_TYPE)
        .constant('text/plain')
    .log('Sent message to Telegram: ${body}')
