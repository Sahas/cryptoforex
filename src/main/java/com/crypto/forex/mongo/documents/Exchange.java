package com.crypto.forex.mongo.documents;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.crypto.forex.entities.Coins;

@Document(collection="exchanges")
@XmlRootElement(name = "exchange")
@XmlAccessorType(XmlAccessType.FIELD)
public class Exchange {

  @Id
  @XmlElement(required = true)
  private String id;

  @XmlElement(required = true)
  private String name;

  @XmlElement(required = true)
  private List<Countries> tradingCountries = new ArrayList<>();

  @XmlElement(namespace = "urn:Crypto.Forex", required = true)
  private List<Coin> coins = new ArrayList<>();;

  @XmlElement(namespace = "urn:Crypto.Forex", required = true)
  private List<CoinPrice> coinPrices = new ArrayList<>();

  /**
   * Gets the value of the id property.
   * 
   * @return
   *     possible object is
   *     {@link String }
   *     
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   * 
   * @param value
   *     allowed object is
   *     {@link String }
   *     
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Gets the value of the name property.
   * 
   * @return
   *     possible object is
   *     {@link String }
   *     
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   * 
   * @param value
   *     allowed object is
   *     {@link String }
   *     
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Gets the value of the tradingCountries property.
   * 
   * @return
   *     possible object is
   *     {@link Exchange.TradingCountries }
   *     
   */
  public List<Countries> getTradingCountries() {
    return tradingCountries;
  }

  /**
   * Sets the value of the tradingCountries property.
   * 
   * @param value
   *     allowed object is
   *     {@link Exchange.TradingCountries }
   *     
   */
  public void setTradingCountries(List<Countries> value) {
    this.tradingCountries = value;
  }

  public void addTradingCountry(Countries country) {
    this.tradingCountries.add(country);
  }

  public void addTradingCountry(String countryName) {
    this.tradingCountries.add(Countries.valueOf(countryName));
  }

  public void removeTradingCountry(Countries country) {
    this.tradingCountries.remove(country);
  }

  /**
   * Gets the value of the coins property.
   * 
   * @return
   *     possible object is
   *     {@link Coins }
   *     
   */
  public List<Coin> getCoins() {
    return coins;
  }

  /**
   * Sets the value of the coins property.
   * 
   * @param value
   *     allowed object is
   *     {@link Coins }
   *     
   */
  public void setCoins(List<Coin> value) {
    this.coins = value;
  }

  public void addCoin(Coin coin) {
    this.coins.add(coin);
  }

  public void removeCoin(Coin coin) {
    this.coins.remove(coin);
  }

  /**
   * Gets the value of the coinPrices property.
   * 
   * @return
   *     possible object is
   *     {@link CoinPrices }
   *     
   */
  public List<CoinPrice> getCoinPrices() {
    return coinPrices;
  }

  /**
   * Sets the value of the coinPrices property.
   * 
   * @param value
   *     allowed object is
   *     {@link CoinPrices }
   *     
   */
  public void setCoinPrices(List<CoinPrice> value) {
    this.coinPrices = value;
  }

  public void addCoinPrice(CoinPrice coinPrice) {
    this.coinPrices.add(coinPrice);
  }

  public void removeCoinPrice(CoinPrice coinPrice) {
    this.coinPrices.remove(coinPrice);
  }

}
