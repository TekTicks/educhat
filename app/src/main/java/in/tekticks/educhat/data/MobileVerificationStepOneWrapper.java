package in.tekticks.educhat.data;


public class MobileVerificationStepOneWrapper {
    private static String sessionId;
    private static String verificationNode;

    public static String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        MobileVerificationStepOneWrapper.sessionId = sessionId;
    }

    public static String getVerificationNode() {
        return verificationNode;
    }

    public void setVerificationNode(String verificationNode) {
        MobileVerificationStepOneWrapper.verificationNode = verificationNode;
    }

    public MobileVerificationStepOneWrapper(String sessionId, String verificationNode) {
        MobileVerificationStepOneWrapper.sessionId = sessionId;
        MobileVerificationStepOneWrapper.verificationNode = verificationNode;
    }
}
