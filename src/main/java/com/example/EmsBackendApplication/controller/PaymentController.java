package com.example.EmsBackendApplication.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import io.lettuce.core.json.JsonObject;
import lombok.Value;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/payment")
public class PaymentController {

//    @Autowired
//    private RazorpayClient razorpayClient;

    // creating order for payment
    @GetMapping("/create_order")
    public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException, JSONException {
        int amount = Integer.parseInt(data.get("amount").toString());
        RazorpayClient razorpayClient = new RazorpayClient("","");
        JSONObject object = new JSONObject();
        object.put("amount",amount*100);
        object.put("currency","INR");
        object.put("receipt","txn_235425");
        System.out.print("Hey oder function ex");

        // creating new order --> req go to razor server
        Order order = razorpayClient.orders.create(object);
        System.out.println(order); // This can be store to db.
        String id = order.get("id");
        return order.toString();
//        return "done";


    }


//    @Value("${razorpay.key.secret}")
    private String keySecret;

    public boolean verifyPaymentSignature(Map<String, String> paymentDetails) throws JSONException {
        String orderId = paymentDetails.get("razorpay_order_id");
        String paymentId = paymentDetails.get("razorpay_payment_id");
        String signature = paymentDetails.get("razorpay_signature");

        // Concatenate the order ID and payment ID with a pipe '|'
        JSONObject object = new JSONObject();
        object.put("orderId",orderId);
        object.put("paymentId",paymentId);
//        String payload = orderId + "|" + paymentId;

        try {
            // The Razorpay SDK provides a utility to perform the HMAC-SHA256 signature check
            boolean isSignatureValid = Utils.verifyPaymentSignature(object, keySecret);

            if (isSignatureValid) {
                // IMPORTANT: Update your database here to mark the order as PAID/CAPTURED
                System.out.println("Signature Verified. Payment is authentic!");
            }
            return isSignatureValid;

        } catch (RazorpayException e) {
            e.printStackTrace();
            return false;
        }
    }
}
//
//
////import com.razorpay.Order;
////import com.razorpay.RazorpayClient;
////import com.razorpay.RazorpayException;
////import org.json.JSONObject; // You need this import
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RestController; // Assuming this is a REST controller
////import org.springframework.web.bind.annotation.GetMapping; // Or use @PostMapping for creation
////
////import java.util.Map;
////
////@RestController
////public class PaymentController {
////
////    // 💡 Best Practice: Autowire the client configured in a separate class
////    @Autowired
////    private RazorpayClient razorpayClient;
////
////    @GetMapping("/create_order") // Use @PostMapping if you are changing state
////    public String createOrder(@RequestBody Map<String, Object> data) throws RazorpayException {
////        // You should validate that 'amount' is present before parsing
////        int amount = Integer.parseInt(data.get("amount").toString());
////
////        // 1. Prepare the request payload
////        JSONObject object = new JSONObject();
////        object.put("amount", amount * 100); // Razorpay requires amount in smallest unit (paise)
////        object.put("currency", "INR");
////        object.put("receipt", "txn_235425");
////
////        System.out.println("Attempting to create order...");
////
////        // 2. Create the order
////        // This relies on the autowired 'razorpayClient'
////        Order order = razorpayClient.orders.create(object);
////
////        // 3. Extract the Order ID and other details for logging/database
////        String orderId = order.get("id"); // Retrieve the ID as a String
////        System.out.println("Order created successfully. Razorpay Order ID: " + orderId);
////
////        // 4. Return the full order object (usually its JSON string) to the frontend
////        return order.toString();
////    }
////}