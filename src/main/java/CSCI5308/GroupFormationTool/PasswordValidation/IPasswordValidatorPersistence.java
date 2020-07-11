package CSCI5308.GroupFormationTool.PasswordValidation;

import java.util.HashMap;
import java.util.List;

public interface IPasswordValidatorPersistence 
{
	public HashMap<Long,String> loadActivePasswordValidators() throws Exception;

	public String loadConstraintByValidatorId(long id) throws Exception;
	
	public List<String> fetchPreviousPasswordsByBannerID(String bannerID, int constraint) throws Exception;

}
