class JoinShopQueueEvent extends JoinQueueEvent { 
    private Shop shop;
    private Customer c;
    public JoinShopQueueEvent(Customer c, Shop shop) { 
        super(c, shop.getQueue());
        this.shop = shop;
        this.c = c;
    }

    @Override
    public String toString() { 
        return String.format("%s: %s joined shop queue %s ",
            super.toString(), this.c, this.shop.getQueue());
    }
}
