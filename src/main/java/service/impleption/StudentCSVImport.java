package service.impleption;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import dao.Course;
import dao.User;
import exception.CopyCatMeDBConfigException;
import exception.CourseGroupFormationException;
import repository.CourseRoleDatabaseRepository;
import repository.UsersDatabaseRepository;
import repository.impl.UsersDatabaseRepositoryImpl;
import service.impl.UserValidator;
import util.IPasswordEncryption;
import util.SystemConfig;

public class StudentCSVImport implements util.Constants
{
	
	private List<String> successResults;
	private List<String> failureResults;
	private Course course;
	private CourseRoleDatabaseRepository courseImpl;
	private MultipartFile uploadedFile;
	private IPasswordEncryption passwordEncryption;
	
	UsersDatabaseRepository userDBImpl = new UsersDatabaseRepositoryImpl();
	
	public StudentCSVImport(Course course, MultipartFile file)
	{
		this.course = course;
		this.uploadedFile = file;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		passwordEncryption = SystemConfig.instance().getPasswordEncryption();
		parseCSVFile();
	}
	
	private void parseCSVFile()
	{
		try
		{
			Reader reader = new InputStreamReader(uploadedFile.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).build();
			List<String[]> records = csvReader.readAll();
			Iterator<String[]> iter = records.iterator();
			while (iter.hasNext())
			{
				String[] record = iter.next();
				enrollStudentFromRecord(record);
			}
		}
		catch (IOException e)
		{
			failureResults.add("Failure reading uploaded file: " + e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			failureResults.add("Failure parsing CSV file: " + e.getMessage());
		}
	}
	
	private void enrollStudentFromRecord(String[] record) throws CopyCatMeDBConfigException, CourseGroupFormationException
	{
		if (record.length != 4)
		{
			failureResults.add("Faulty row: " + Arrays.toString(record));
			return;
		}
		User u = new User();
		u.setBannerId(record[0]);
		u.setFirstName(record[1]);
		u.setLastName(record[2]);
		u.setEmailId(record[3]);
		
		if (UserValidator.validateUser(u))
		{			
			if (userDBImpl.createUser(u, passwordEncryption, true))
			{
				successResults.add("Created: " + Arrays.toString(record));
			}
			else
			{
				failureResults.add("Unable to save this user to DB: " + Arrays.toString(record));
				return;
			}
			
			System.out.println(u.getId() + "User ID");
			try {
				courseImpl = SystemConfig.instance().getCourseRole();
				if (courseImpl.enrollUserInCourse(u.getId(), course.getCourseId(), 3))
					failureResults.add("Unable to enroll user in course: " + Arrays.toString(record));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else {
			failureResults.add("Unable to enroll user in course: " + Arrays.toString(record));
		}
	}
	
	public List<String> getSuccessResults()
	{
		return successResults;
	}
	
	public List<String> getFailureResults()
	{
		return failureResults;
	}
}
