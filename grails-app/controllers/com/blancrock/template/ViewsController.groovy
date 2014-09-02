package com.blancrock.template

/**
 * Created by Vladimir Havenchyk.
 */
class ViewsController {
    def index() {
        render template: '/views/index'
    }

    def market() {
        render template: '/views/market'
    }

    def marketData() {
        render template: '/marketData/marketData'
    }

    def account() {
        render template: '/views/account'
    }

    def help() {
        render template: '/views/help'
    }

    def about() {
        render template: '/views/about'
    }

    def accountTrade() {
        render template: '/account/trade/trade'
    }

    def accountTradeOverview() {
        render template: '/account/trade/overview/index'
    }

    def accountTradeNewOrder() {
        render template: '/account/trade/newOrder/newOrder'
    }

    def accountTradeOrders() {
        render template: '/account/trade/orders/orders'
    }

    def accountTradeShowOrderDetails() {
        render template: '/account/trade/showOrderDetails'
    }

    def accountTradePositions() {
        render template: '/account/trade/positions/positions'
    }

    def accountTradeTrades() {
        render template: '/account/trade/trades/trades'
    }

    def accountTradeTradeDetails() {
        render template: '/account/trade/tradeDetails'
    }

    def accountFunding() {
        render template: '/account/funding/funding'
    }

    def accountSecurity() {
        render template: '/account/security/index'
    }

    def accountSettings() {
        render template: '/account/settings/index'
    }

    def accountHistory() {
        render template: '/account/history/history'
    }

    def accountHistoryLedgers() {
        render template: '/account/history/ledgers/ledgers'
    }

    def accountHistoryLedgersDetails() {
        render template: '/account/history/ledgers/ledgerDetails'
    }

    def accountGetVerified() {
        render template: '/account/getVerified/index'
    }

    def accountFundingAudit() {
        render template: '/account/funding/audit/audit'
    }

    def accountFundingWithdraw() {
        render template: '/account/funding/withdraw/recentWithdrawalsInterface'
    }

    def accountFundingWithdrawDetails(){
        render template: '/account/funding/withdraw/withdrawDetailsInterface'
    }

    def accountFundingDeposit() {
        render template: '/account/funding/deposit/recentDepositsInterface'
    }

    def accountFundingDepositCurrency(){
        render template: '/account/funding/deposit/depositDetailsInterface'
    }

    def accountSecurityPassword() {
        render template: '/account/security/password/index'
    }

    def accountSecurityTFA() {
        render template: '/account/security/tfa/index'
    }

    def accountTradeNewOrderOpenOrders() {
        render template: '/account/trade/newOrder/newAndOpenOrders'
    }

    def accountTradeNewOrderPositions() {
        render template: '/account/trade/newOrder/positions'
    }

    def accountTradeNewOrderBook() {
        render template: '/account/trade/newOrder/orderBook'
    }

    def accountTradeNewOrderSimple() {
        render template: '/account/trade/newOrder/simpleOrder'
    }

    def accountTradeNewOrderSimpleDetails() {
        render template: '/account/trade/newOrder/orderDetails'
    }

    def accountTradeNewOrderIntermediate() {
        render template: '/account/trade/newOrder/intermediateOrder'
    }

    def accountTradeNewOrderAdvanced() {
        render template: '/account/trade/newOrder/advancedOrder'
    }

    def login() {
        render template: '/login/login'
    }

    def logout() {
        render template: '/login/logout'
    }

    def signUp() {
        render template: '/login/signUp'
    }
}
