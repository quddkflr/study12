package com.example.demo.filter;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/*
* 1. gateway의 기능 중 필터 기능을 넣어 보도록 하겠습니다.
* 2. ZuulFilter 클래스를 상속받는 클래스를 구현합니다.
* 3. 4개의 오버라이딩 메서드를 통해서 원하는 작업을 합니다
*/
@Component
public class ZuulFilterImple extends ZuulFilter {
	private static final Logger logger = LoggerFactory.getLogger(ZuulFilterImple.class);

//필터가 실행될지 여부를 결정합니다.
//반환 값으로 true를 반환하면 필터가 실행되며, false를 반환하면 필터가 실행되지않습니다.
	@Override
	public boolean shouldFilter() {
		return true;
	}

//필터가 수행할 기능을 이 메서드에 작성합니다.
	@Override
	public Object run() throws ZuulException {
		logger.info("========= Object run()1 ============");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info(request.getRequestURI());
		logger.info("========= Object run()2 ============");
		return null;
	}

	@Override
	/*
	 * 이 메서드는 필터의 유형을 정의합니다. 반환 값으로 "pre", "post" 등을 사용할 수 있습니다. "pre"는 라우팅 젂에 실행되는
	 * 필터를 나타내며, "post"는 라우팅 후에 실행되는 필터를 의미하고,
	 */ public String filterType() {
		return "pre"; // 사젂 라우팅 필터링을 위한 "pre"
	}

	/*
	 * 이 메서드는 필터의 실행 순서를 결정합니다. 필터가 여러 개인 경우 이 순서에 따라 실행됩니다. 숫자가 낮을수록 우선순위가 높습니다
	 */
	@Override
	public int filterOrder() {
		return 1; // 작은 값이 우선권을 가집니다.
	}
}