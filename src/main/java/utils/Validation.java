package utils;

import io.jsonwebtoken.*;
import models.User;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Validation {

    public static boolean validatePasswords(String password1, String password2){
        return password1.equals(password2);
    }


    public static boolean validateUser(String email, String password) {
        Connection db;
        PreparedStatement ps;
        User user = null;
        try {
            db = DBUtils.getINSTANCE().getConnection();
            String query = "SELECT password,salt FROM biodiscovery.user WHERE email = ?";
            ResultSet rs;
            ps = db.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                user.setPassword(rs.getString("password"));
                user.setSalt(rs.getString("salt"));
            }
            if (user != null) {
                return user.getPassword().equals(SecureUtils.makePassword(password, user.getSalt()));
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isAuthenticated(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (String) session.getAttribute("email") != null;
    }

    public static boolean isSuperUser(HttpServletRequest request){
        HttpSession session = request.getSession();/*Getting the session*/
        String email = (String)session.getAttribute("email");/*getting the string attribute saved in the session from login*/
        if((String)session.getAttribute("email") != null){ /*If the value of the email is not null*/
            Connection db;
            PreparedStatement ps;
            User user = null;
            try {
                db = DBUtils.getINSTANCE().getConnection();
                String query = "SELECT is_superuser FROM biodiscovery.user WHERE email = ? and is_superuser = 1";
                ResultSet rs;
                ps = db.prepareStatement(query);
                ps.setString(1, email);
                rs = ps.executeQuery();

                while (rs.next()) {
                    user = new User();
                    user.set_superuser(rs.getBoolean("is_superuser"));
                }
                return user != null;
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    public static boolean validateKey(String key){
        return key.equals("hei");
    }





    public static Jws<Claims> decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)

        return Jwts.parserBuilder()
                .setSigningKey("secretkeygroup11amvuiasecretkeygroup11amvuiasecretkeygroup11amvuiasecretkeygroup11amvuia")
                .build()
                .parseClaimsJws(jwt);
    }
    public static String createToken(String email) {
        String issuer = "amv";
        String subject = "reset";
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long thenmillis = System.currentTimeMillis() +( 24 * 60 * 60 * 1000);
        Date then = new Date(thenmillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secretkeygroup11amvuiasecretkeygroup11amvuiasecretkeygroup11amvuiasecretkeygroup11amvuia");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(email)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey).setExpiration(then);



        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
    public static void main( String[] args) throws NoSuchFieldException, IllegalAccessException {
        User user = new User();
        user.setId(1);
        String token = createToken("issa@gmail.com");
        try {

            Jws<Claims> claims = decodeJWT(token);
            System.out.println(token);
            System.out.println(claims.getBody().getId());
            System.out.println(claims.getBody().getExpiration());
            System.out.println(claims.getBody());
        }catch(ExpiredJwtException e){
            System.out.println("token expired");

        }catch(SignatureException e){
            System.out.println("Invalid token");

        }

        long nowMillis = System.currentTimeMillis() +( 24 * 60 * 60 * 1000);
        Date now = new Date(nowMillis);
        System.out.println(now);



    }



}

