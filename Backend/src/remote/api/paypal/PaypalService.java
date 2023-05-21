package remote.api.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import java.util.ArrayList;
import java.util.List;

public class PaypalService {
    private static final String CLIENT_ID = "AU_stpGqG_UlgP-5kB8zurC_jBXEJlrEguApG-v4D0WX6ti9fHnLyF5gAF53eh3gJyLSgQl9RwC76cpx";
    private static final String CLIENT_SECRET = "EJg7DQLNC5o1O2uShFN3Hjbo8hIxZJIaJV-K2PoYVVmD99B_ho6SqFXrdzmo936xuchiKSGwNiRvze7u";
    private static final String MODE = "sandbox";

    public void createPayment(String precio) {
        Amount amount = new Amount();
        amount.setCurrency("EUR");
        amount.setTotal(precio);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        // Configure the APIContext
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        try {
            Payment createdPayment = payment.create(apiContext);
            if (createdPayment == null) {
                System.out.println("Payment is null");
            } else {
                System.out.println("Payment ID: " + createdPayment.getId());
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
    }
}
