package quru.qa.niffler.jupiter;

import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import quru.qa.niffler.api.SpendApiClient;
import quru.qa.niffler.model.CategoryJson;
import quru.qa.niffler.model.SpendJson;

import java.util.Date;

public class SpendExtension implements BeforeEachCallback, ParameterResolver {
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(SpendExtension.class);

    private final SpendApiClient spendApiClient = new SpendApiClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Spend.class)
                .ifPresent(
                        annotation -> {
                            SpendJson spendJson = new SpendJson(
                                    null,
                                    new Date(),
                                    new CategoryJson(
                                            null,
                                            annotation.category(),
                                            annotation.username(),
                                            false),
                                    annotation.currency(),
                                    annotation.amount(),
                                    annotation.description(),
                                    annotation.username()
                            );
                            SpendJson createdSpend = spendApiClient.createSpend(spendJson);
                            context.getStore(NAMESPACE).put(context.getUniqueId(), createdSpend);
                        }
                );
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(Spend.class);
    }

    @Override
    public SpendJson resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(
                extensionContext.getUniqueId(),
                SpendJson.class
        );
    }
}
