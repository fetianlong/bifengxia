package com.chatm.search.model;

import java.math.BigDecimal;

public class CommodityPrice {
    private Long id;

    private String name;
    
    private String ticketId;

    private Long comId;

    private BigDecimal oldPrice;

    private BigDecimal newPrice;
    
    private String oldPriceString;
    
    private String newPriceString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getComId() {
        return comId;
    }

    public void setComId(Long comId) {
        this.comId = comId;
    }

	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	public BigDecimal getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getOldPriceString() {
		return oldPriceString;
	}

	public void setOldPriceString(String oldPriceString) {
		this.oldPriceString = oldPriceString;
	}

	public String getNewPriceString() {
		return newPriceString;
	}

	public void setNewPriceString(String newPriceString) {
		this.newPriceString = newPriceString;
	}

}