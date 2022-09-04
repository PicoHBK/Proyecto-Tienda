package back.security.util;

import java.io.Serializable;

public class TokenInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String jwtToken;
 
  private final long id;


  public TokenInfo(String jwtToken, long id) {
    this.jwtToken = jwtToken;
    this.id = id;
  }

  public String getJwtToken() {
    return this.jwtToken;
  }

  public long getId() {
    return this.id;
  }

}