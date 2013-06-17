package com.pis.common.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:/spring/*.xml" })
public abstract class PisTestCase extends AbstractJUnit4SpringContextTests {
	protected Log log = LogFactory.getLog(this.getClass());
}
