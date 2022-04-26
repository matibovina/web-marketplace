package com.springboot.web.lucila.app.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "clave.alguna.watch.12345";
	
	public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4tY7LVS3iYXf58FoGkzl\n"
			+ "BTw1G+KDU2gb4uoHF8UZXLnWax5XIrWuiNUO8ahzYW3mWa+rFQWfmgDI99DjSNX8\n"
			+ "6Yw8p2pPDFrFigGT/O9DzTgVhjEKctvfyFzAxXbMkcaS9QBNZyMfJ9rRFFFP7qRP\n"
			+ "iKmxqW2B7du3k7lO2S1QcX9VJLqRn+tmYLGrO739ynj7sLOxm/vG/LloQp2UY0ON\n"
			+ "MN/bWvPhp56sOnPFE5UrvGDYMn5Hds0Wo99VfWZb92ikJyKN08R20mNii3k5MKhu\n"
			+ "oczn3SaDzZNjHYLuD92+vsHmYLsgz0SueM7VdByNXg1y9oUjWdTJYYzlXRtY1pKE\n"
			+ "FQIDAQAB\n"
			+ "-----END PUBLIC KEY-----";
	
	public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEpAIBAAKCAQEA4tY7LVS3iYXf58FoGkzlBTw1G+KDU2gb4uoHF8UZXLnWax5X\n"
			+ "IrWuiNUO8ahzYW3mWa+rFQWfmgDI99DjSNX86Yw8p2pPDFrFigGT/O9DzTgVhjEK\n"
			+ "ctvfyFzAxXbMkcaS9QBNZyMfJ9rRFFFP7qRPiKmxqW2B7du3k7lO2S1QcX9VJLqR\n"
			+ "n+tmYLGrO739ynj7sLOxm/vG/LloQp2UY0ONMN/bWvPhp56sOnPFE5UrvGDYMn5H\n"
			+ "ds0Wo99VfWZb92ikJyKN08R20mNii3k5MKhuoczn3SaDzZNjHYLuD92+vsHmYLsg\n"
			+ "z0SueM7VdByNXg1y9oUjWdTJYYzlXRtY1pKEFQIDAQABAoIBACY1Tax6C5xHh4qD\n"
			+ "AmUDtMPn+04iHlrLO39CSkWPtpDu4JYUezrmfWqg8K514FF18IoE0/1rgBGxtig/\n"
			+ "8DjRipNQQts0RfJfy8xOLb46EyKbIs0Cr67fKFeYgAQC2bxoRVKtZVxNBy9wZS1V\n"
			+ "8O/Sj28Gnl1ai7sj+k0Qtp5QSq+DIQbbEWZ0q2AjeeCOk2hnc7KenxsBMD1n3voW\n"
			+ "SNzsDaI2JJo1Q/WZbfZgYWHFKNyW5d9NYjf7wHMbQasllb4XPYxiT7uznb5yv5Ps\n"
			+ "Gdb0GqFqVocLr/MSbxz3YRPEOvL9Y9sBvRDupId2Jj5aSrvdIVZMzs+zMHhpFsUI\n"
			+ "IB9nIJUCgYEA8YfdOdgFiSKjOUSHKUX5+D4SjsMwWIitcw8+dR12+Pjzlzg2CmG9\n"
			+ "7HTeIjkxBE5hNyc6tUMaBZHtvOOxuviE3jLmR1r8rSq67yiGupE+p3bc7Lq6RuB2\n"
			+ "7Cp9UVejH3NBpRbJT0/YHFGr+VraClN2L3bTB9tc/TqIzMMB3QDKcPsCgYEA8G0F\n"
			+ "OYFN7IeHAyMyMV9w41m3JnkmctKRkph54pn7jNCFiVnqM1G4ISc7VoGm28PPGpSv\n"
			+ "WTbglGDGa6vJzexWdWwQZWhaewOwkRnnk9FMGpJqCLEnx5xsKob9N1m+ZbUyCXu4\n"
			+ "iT5nmPH9sTUxI+MZwIJEOmsal0Oev9ldnSXIci8CgYA8OSlDJAb1U6qpO9oLw4P5\n"
			+ "jtinuimS2bmN5+4l24vvtnteaEipPAfULqu4ktQTA8wfqWttCOczHljN8WQ+f3r5\n"
			+ "wG1Eu34nW3Ey8sLtQAwES5H4gPKze4KLjmii4Jwu2lky5cSaPwlevkqIKUwWqQrd\n"
			+ "+N3TprG//llf3PvY9DqGAwKBgQC7KYNpsrlAqnh1/s4yFURCGDM1phZCSJ2+ayhn\n"
			+ "OMvUYPezZuWksbcPSAUvYJrOde4hmSGv58KIf5W3d/IPdi4QFuHkr4nQPZ0UOABI\n"
			+ "2CPQg647uf1nrOOfb5prnldTweyD6WZMoOHw9GEztF7euwnvDPmkKIB4xNEuND5l\n"
			+ "+WXDTQKBgQClsRDpfndqK2EvybNcOenS95T+ul4Rb3AYCcwAGfgmWGrt6E8Msdvs\n"
			+ "DSSE0t4Nzs5eISgX8ss5HQKDoAGqVwum6soCjOSozvNeQSjbEnpBtZXaR9z2ny4K\n"
			+ "F4gfIorIO3p+alfAC1aCFzGFoRYuQFZFK+QU4KT2ktRi3TAgg3Aa1w==\n"
			+ "-----END RSA PRIVATE KEY-----";

}
