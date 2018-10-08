package pcs.ub.edu.ar.clinicavirtual.factory.JsonFactory;

import org.json.JSONException;
import org.json.JSONObject;



public class JsonUserInfo {


    private Integer mID;
    private String  mName;
    private String  mEmail;
    private String  mEmailVerifiedAt;
    private String  mCreatedAt;
    private String  mUpdatedAt;
    private String  mAPIToken;
    private Integer mActive;

    private JSONObject jsonObject;


    public JsonUserInfo(JSONObject jsonObject) throws JSONException {
        setJsonObject(jsonObject.getJSONObject("data"));
        initAtributes();
    }

    private void initAtributes() throws JSONException {

        setmID(getJsonObject().getInt("id"));
        setmName(getJsonObject().getString("name"));
        setmEmail(getJsonObject().getString("email"));
        setmEmailVerifiedAt(getJsonObject().getString("email_verified_at"));
        setmCreatedAt( getJsonObject().getString("created_at"));
        setmUpdatedAt( getJsonObject().getString("updated_at"));
        setmAPIToken(getJsonObject().getString("api_token"));
        setmActive(getJsonObject().getInt("active"));


    }

    public Integer getmID() {
        return mID;
    }

    private void setmID(Integer mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    private void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    private void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmEmailVerifiedAt() {
        return mEmailVerifiedAt;
    }

    private void setmEmailVerifiedAt(String mEmailVerifiedAt) {
        this.mEmailVerifiedAt = mEmailVerifiedAt;
    }

    public String getmCreatedAt() {
        return mCreatedAt;
    }

    private void setmCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

    public String getmUpdatedAt() {
        return mUpdatedAt;
    }

    private void setmUpdatedAt(String mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
    }

    public String getmAPIToken() {
        return mAPIToken;
    }

    private void setmAPIToken(String mAPIToken) {
        this.mAPIToken = mAPIToken;
    }

    public Integer getmActive() {
        return mActive;
    }

    private void setmActive(Integer mActive) {
        this.mActive = mActive;
    }

    private void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
