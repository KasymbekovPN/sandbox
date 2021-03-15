package org.kpn.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class ProfilingDemo {
    public static void main(String[] args) {
        WorkerBean workerBean = getWorkerBean();
        workerBean.doSomeWork(10_000_000);
    }

    private static WorkerBean getWorkerBean(){
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(new WorkerBean());
        pf.addAdvice(new ProfilingInterceptor());

        return (WorkerBean) pf.getProxy();
    }
}
