package mandelbrot_graal;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.HashMap;
import java.util.Map;

public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        final LambdaLogger logger = context.getLogger();

        final APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            logger.log("Received request: " + input.toString());
            final String iterations = input.getQueryStringParameters().get("iterations");
            MandelBrot.run(Integer.parseInt(iterations), logger::log);
            return response
                    .withStatusCode(200)
                    .withBody("{\"message\":\"success\"}");
        } catch (final Exception e) {
            logger.log("Caught unhandled exception: " + e.getLocalizedMessage());
            return response
                    .withBody("{}")
                    .withStatusCode(500);
        }
    }
}
