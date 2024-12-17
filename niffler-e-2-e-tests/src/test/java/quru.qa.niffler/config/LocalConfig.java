package quru.qa.niffler.config;

public enum LocalConfig implements Config {
    instance;

    @Override
    public String frontUrl() {
        return "https://auth.niffler.qa.guru/login";
    }

    @Override
    public String spendUrl() {
        return "https://api.niffler.qa.guru/api/spends/add";
    }
}
