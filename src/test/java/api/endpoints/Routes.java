package api.endpoints;

public class Routes {

	public static String base_url="http://localhost:3000/users";
	//users module
	public static String post_url= base_url;
	public static String get_url= base_url+"/{userId}";
	public static String update_url= base_url+"/{userId}";
	public static String delete_url= base_url+"/{userId}";
}
