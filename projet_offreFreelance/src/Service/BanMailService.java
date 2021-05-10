package Service;

import java.util.Date;

public class BanMailService {
    public void BanMailer(String mail, Date banDate, String prenom ,String Username , String Description) {
    String from = "zahertestamri@gmail.com";
    String pass = "swdzaher2050";
    String[] to = {"" + mail}; // list of recipient email addresses
    String subject = "ACCOUNT BAN ";
    String body = "Greetings Mr "+prenom.toUpperCase()+" "+Username.toUpperCase()+ " We would like to inform you that you account has been suspended on : "+banDate+" For : "+Description+" . \n For more Please CONTACT our CLIENT SUPPORT AT INFIJOB.COM/SUPPORT \n Best Regards \n ~NOTZAHER";
    MailService serv = new MailService();
    serv.sendFromGMail(from,pass,to,subject,body);
}
}
