package mybatisMuti.mapper_oracle;

import com.didispace.App;
import com.didispace.mybatisMuti.mapper_oracle.M000_menuMapper;
import com.didispace.web.M000_beans.Menu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class M000_menuMapperTest {

    @Autowired
    private M000_menuMapper mapper;

    @Test
    public void testSelectSubMenu(){

        List<Menu> list = mapper.selectSubMenu("M001");

        list.stream().map(Menu::toStringPretty).forEach(log::info);

        Assert.assertNotNull(list);

    }
}
