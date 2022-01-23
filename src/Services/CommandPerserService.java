package Services;
import Tools.JSONtool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import entity.Account;
import entity.Request;
import requestsFormats.Comment;
import requestsFormats.ForOther;
import requestsFormats.LogIn;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Scanner;
/**
 * This class defines command perser service
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.30.2021
 * */
public interface CommandPerserService {

    /**
     * timeline request
     *
     * @return request
     */
    public static String timeLine() {
        return null;
    }

    /**
     * @param who data
     * @return request
     */
    public static String action(String who) {
        return null;
    }

    /**
     * request for profile
     *
     * @return request
     */
    public static String profile() {
        return null;
    }

    /**
     * request for unfollow
     *
     * @return request
     */
    public static String unfollow() {
        return null;
    }

    /**
     * request for follow
     *
     * @return request
     */
    public static String follow() {
        return null;
    }

    /**
     * request for comment
     *
     * @return request
     */
    public static String comment() {
        return null;
    }

    /**
     * request for retweet
     *
     * @return request
     */
    public static String retweet() {
        return null;
    }

    /**
     * request for like
     *
     * @return request
     */
    public static String like() {
        return null;
    }

    /**
     * request for remove
     *
     * @return request
     */
    public static String remove() {
        return null;
    }

    /**
     * request for TWEET
     *
     * @return request
     */
    public static String tweet() {
        return null;
    }

    /**
     * request for logOut
     *
     * @return request
     */
    public static String logOut() {
        return null;
    }

    /**
     * request for logIn
     *
     * @return request
     * @throws JsonProcessingException exception
     */
    public static String logIn() throws JsonProcessingException {
        return null;
    }

    /**
     * @return request for sign up
     * @throws NoSuchAlgorithmException  e
     */
    public static String SignUp() throws NoSuchAlgorithmException {
        return null;
    }

    /**
     * @param password data
     * @return Hash of password
     * @throws NoSuchAlgorithmException for checking algorithm
     */
    public static String toHash(String password) throws NoSuchAlgorithmException {
        return null;
    }

    /**
     * @param input string
     * @return bytes
     * @throws NoSuchAlgorithmException for checking algorithm
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        return new byte[0];
    }


    /**
     * @param hash bytes
     * @return string
     */
    public static String toHexString(byte[] hash) {
        return null;
    }

}
