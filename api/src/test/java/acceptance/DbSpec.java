package acceptance;


import io.restassured.RestAssured;

import java.io.*;
import java.util.*;

import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.*;
import org.assertj.db.type.*;
import org.json.simple.*;


import static org.assertj.db.api.Assertions.assertThat;


abstract class DbSpec {
  Changes changes() {
    return new Changes(source());
  }

  void configRestAssured(
    final String uri, final Integer port, final String path
  ) {
    RestAssured.baseURI = uri;
    RestAssured.port = port;
    RestAssured.basePath = path;
  }

  void configRestAssured() {
    configRestAssured("http://localhost", 8080, "");
  }

  void assertNumberOfChangesIsOn(
    final Changes changes, final Integer number, final String... tables
  ) {
    Arrays.asList(tables).stream().forEach(table -> {
      assertThat(changes).onTable(table).hasNumberOfChanges(number);
    });
  }

  String params(final Pair... pairs) {
    final JSONObject params = new JSONObject();
    Arrays.asList(pairs).stream().forEach(pair -> {
      params.put(pair.key, pair.value);
    });
    return params.toJSONString();
  }

  Pair param(final String key, final Object value) {
    return new Pair(key, value);
  }

  final class Pair {
    public String key;
    public Object value;
    public Pair(final String key, final Object value) {
      this.key = key;
      this.value = value;
    }
  }

  void execute(final String sqlfile) {
    final File source = new File(
      getClass().getClassLoader().getResource(sqlfile).getFile()
    );
    final class SQLExecuter extends SQLExec {
      public SQLExecuter() {
        final Project project = new Project();
        project.init();
        setProject(project);
        setTaskType("sql");
        setTaskName("sql");
      }
    }
    final SQLExecuter executer = new SQLExecuter();
    executer.setSrc(source);
    executer.setDriver(driver());
    executer.setPassword(password());
    executer.setUserid(user());
    executer.setUrl(url());
    executer.execute();
  }

  Source source() {
    return new Source(url(), user(), password());
  }

  String url() {
    return System.getenv(
      "DB_URL"
    );
  }

  String user() {
    return System.getenv(
      "DB_USER"
    );
  }

  String password() {
    return System.getenv(
      "DB_PASSWORD"
    );
  }

  String driver() {
    return "org.postgresql.Driver";
  }
}
