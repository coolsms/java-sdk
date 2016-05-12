package net.nurigo.java_sdk.api;

import java.util.HashMap;

import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.nurigo.java_sdk.exceptions.CoolsmsSDKException;

import org.json.simple.JSONObject;

/**
 * @class Image
 * @brief management image, using Rest API
 */
public class Image extends Coolsms {	
	/**
	 * @brief constructor
	 */	
	public Image(String api_key, String api_secret) {
		super(api_key, api_secret);		
	}

	/**
	 * @brief get image list( HTTP Method GET )
	 * @param hashmap<string, string> params {
	 *   @param integer offset [optional]
	 *   @param integer limit [optional] }
	 * @return JSONObject
	 * @throws CoolsmsException
	 */
	public JSONObject getImageList(HashMap<String, String> params)  throws CoolsmsException {		
		return request("image_list", params);
	}

	/**
	 * @brief get image info ( HTTP Method GET )
	 * @param string image_id [required]
	 * @return JSONObject
	 * @throws CoolsmsException
	 */
	public JSONObject getImageInfo(String image_id) throws CoolsmsException {		
		HashMap<String, String> params = new HashMap<String, String>();
		String resource = "images/" + image_id;
		return request(resource, params);
	}

	/**
	 * @brief upload image ( HTTP Method POST )
	 * @param hashmap<string, string> params {
	 *   @param string image [required]
	 *   @param string encoding [optional] }
	 * @return JSONobject
	 * @throws CoolsmsException
	 */
	public JSONObject uploadImage(HashMap<String, String> params) throws CoolsmsException {
		if (!checkString(params.get("image"))) throw new CoolsmsSDKException("image is required", 202);		
		return postRequest("upload_image", params);		
	}

	/**
	 * @brief delete images ( HTTP Method POST )
	 * @param string image_ids [required]
	 * @return JSONObject
	 * @throws CoolsmsException 
	 */
	public JSONObject deleteImages(String image_ids) throws CoolsmsException {		
		if (!checkString(image_ids)) throw new CoolsmsSDKException("image_ids is required", 202);

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("image_ids", image_ids);
		return postRequest("delete_images", params);
	}
}