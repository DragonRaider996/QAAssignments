public class CustomerSendEmail extends EmailSender {

    public void emailCustomerSpecialOffer(String email)
    {
        String msg = "Congratulations! Your purchase history has earned you a 10% discount on your next purchase!";
        super.sendEmail(email, "10% off your next order!", msg);
    }

}