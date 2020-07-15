package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Reader;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.TestSystemConfig;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVParserTest 
{
	@Test
	public void parseCSVFile() 
	{
		Reader reader = null;
		CSVReaderMock csvReader = TestSystemConfig.instance().getCsvReader();
		List<String[]> records = csvReader.readAll(reader);
		assertThat(records).isNotNull();
		assertThat(records).isNotEmpty();
		Assert.isTrue(records.size() > 0);
	}
}
