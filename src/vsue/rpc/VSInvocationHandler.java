package vsue.rpc;

import vsue.Logger;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Solsagan on 04.07.2017.
 */
public class VSInvocationHandler implements InvocationHandler, Serializable {

    private VSRemoteReference remoteReference;

    public VSInvocationHandler(VSRemoteReference newRemoteReference) {
        this.remoteReference = newRemoteReference;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger.log("[Invoking Method] " + method.getName());
        return method.invoke(proxy, args);
    }
}

