package utils;


import java.util.concurrent.CompletableFuture;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.sunbird.Application;

import org.sunbird.common.models.util.ProjectUtil;
import org.sunbird.util.DBUtil;
import play.api.Environment;
import play.api.inject.ApplicationLifecycle;

/**
 * This class will be called after on application startup. only one instance of this class will be
 * created. StartModule class has responsibility to eager load this class.
 *
 * @author manzarul
 */
@Singleton
public class ApplicationStart {
	public static ProjectUtil.Environment env;

	  /**
	   * All one time initialization which required during server startup will fall here.
	   * @param lifecycle ApplicationLifecycle
	   * @param environment Environment
	   */
	  @Inject
	  public ApplicationStart(ApplicationLifecycle lifecycle, Environment environment) {

		setEnvironment(environment);
		//instantiate actor system and initialize all the actors
		Application.getInstance().init();

		checkCassandraConnections();
		// Shut-down hook
		lifecycle.addStopHook(
		() -> {
		return CompletableFuture.completedFuture(null);
		});
	  }

	private void setEnvironment(Environment environment) {
		if (environment.asJava().isDev()) {
			env = ProjectUtil.Environment.dev;
		} else if (environment.asJava().isTest()) {
			env = ProjectUtil.Environment.qa;
		} else {
			env = ProjectUtil.Environment.prod;
		}
	}

	private static void checkCassandraConnections() {
		DBUtil.checkCassandraDbConnections();
	}
}
