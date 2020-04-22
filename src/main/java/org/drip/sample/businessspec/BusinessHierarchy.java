
package org.drip.sample.businessspec;

import java.util.Set;

import org.drip.capital.definition.Business;
import org.drip.capital.env.CapitalEstimationContextManager;
import org.drip.service.env.EnvManager;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2020 Lakshmi Krishnamurthy
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting analytics/risk, transaction cost analytics,
 *  	asset liability management analytics, capital, exposure, and margin analytics, valuation adjustment
 *  	analytics, and portfolio construction analytics within and across fixed income, credit, commodity,
 *  	equity, FX, and structured products. It also includes auxiliary libraries for algorithm support,
 *  	numerical analysis, numerical optimization, spline builder, model validation, statistical learning,
 *  	and computational support.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Product Core - https://lakshmidrip.github.io/DROP-Product-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Computational Core - https://lakshmidrip.github.io/DROP-Computational-Core/
 * 
 * 	DROP Product Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Loan Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 *  - Asset Liability Management Analytics
 * 	- Capital Estimation Analytics
 * 	- Exposure Analytics
 * 	- Margin Analytics
 * 	- XVA Analytics
 * 
 * 	DROP Computational Core implements libraries for the following:
 * 	- Algorithm Support
 * 	- Computation Support
 * 	- Function Analysis
 *  - Model Validation
 * 	- Numerical Analysis
 * 	- Numerical Optimizer
 * 	- Spline Builder
 *  - Statistical Learning
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
 * 	- Repo Layout Taxonomy     => https://github.com/lakshmiDRIP/DROP/blob/master/Taxonomy.md
 * 	- Javadoc                  => https://lakshmidrip.github.io/DROP/Javadoc/index.html
 * 	- Technical Specifications => https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal
 * 	- Release Versions         => https://lakshmidrip.github.io/DROP/version.html
 * 	- Community Credits        => https://lakshmidrip.github.io/DROP/credits.html
 * 	- Issues Catalog           => https://github.com/lakshmiDRIP/DROP/issues
 * 	- JUnit                    => https://lakshmidrip.github.io/DROP/junit/index.html
 * 	- Jacoco                   => https://lakshmidrip.github.io/DROP/jacoco/index.html
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *   
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

/**
 * <i>BusinessHierarchy</i> zeds the Accounts belonging to a Business. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Bank for International Supervision(2005): Stress Testing at Major Financial Institutions: Survey
 * 				Results and Practice https://www.bis.org/publ/cgfs24.htm
 * 		</li>
 * 		<li>
 * 			Glasserman, P. (2004): <i>Monte Carlo Methods in Financial Engineering</i> <b>Springer</b>
 * 		</li>
 * 		<li>
 * 			Kupiec, P. H. (2000): Stress Tests and Risk Capital <i>Risk</i> <b>2 (4)</b> 27-39
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/PortfolioCore.md">Portfolio Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/CapitalAnalyticsLibrary.md">Capital Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/README.md">DROP API Construction and Usage</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/sample/businessspec/README.md">Business Grouping and Hierarchy Specification</a></li>
 *  </ul>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class BusinessHierarchy
{

	private static final void ListAccountSet (
		final String business)
		throws Exception
	{
		Set<String> accountset = CapitalEstimationContextManager.ContextContainer().accountBusinessContext().accountSet (business);

		System.out.println (
			"\t| "+ business + " => " + accountset.size() + " | " + accountset
		);
	}

	public static final void main (
		final String[] argumentArray)
		throws Exception
	{
		EnvManager.InitEnv ("");

		String[] businessArray =
		{
			Business.ADVISORY,
			Business.AI,
			Business.CAI,
			Business.CAPITAL_MARKETS_ORGANIZATION,
			Business.CAPITAL_MARKETS_ORIGINATION_LENDING,
			Business.CASH,
			Business.CENTRAL_AMERICA_MORTGAGES,
			Business.BHCFINANCIAL,
			Business.COMMERCIAL_REAL_ESTATE,
			Business.COMMODITIES,
			Business.COMMODITIES_HOUSTON,
			Business.CONSUMER_CARDS,
			Business.CONSUMER_OTHER,
			Business.CONVERTS,
			Business.CORPORATE_CENTER,
			Business.CREDIT_MACRO_HEDGE,
			Business.CREDIT_MARKETS,
			Business.CREDIT_TRADING,
			Business.DISTRESSED,
			Business.EM_ABF,
			Business.EM_ASSET_BACKED_FINANCE,
			Business.EM_BONDS,
			Business.EM_CREDIT_TRADING,
			Business.EM_PRIMARY_LOANS,
			Business.EQUITIES,
			Business.EQUITY_DERIVATIVES,
			Business.EQUITY_UNDERWRITING,
			Business.FIMA,
			Business.FINANCE,
			Business.G10_FX,
			Business.G10_RATES,
			Business.GLOBAL_CREDIT_MARKETS,
			Business.GLOBAL_SECURITIZED_MARKETS,
			Business.GSSG_WEST,
			Business.GTS,
			Business.GTS_HOLDINGS_TRADE,
			Business.GWM,
			Business.IG_BONDS,
			Business.IG_PRIMARY_LOANS,
			Business.INTERNATIONAL_CARDS,
			Business.INTERNATIONAL_RETAIL_BANKING,
			Business.LEVERAGED_FINANCE,
			Business.LOCAL_MARKETS,
			Business.LONG_TERM_ASSET_GROUP,
			Business.MUNICIPAL,
			Business.MUNICIPAL_SECURITIES,
			Business.MUNICIPAL_SECURITIES_BHC_COMMUNITY,
			Business.NIKKO_INVESTMENTS,
			Business.OS_B,
			Business.OTHER_BAM,
			Business.OTHER_CONSUMER,
			Business.OTHER_FI_UNDERWRITING,
			Business.OTHER_GLOBAL_MARKETS,
			Business.OTHER_SPECIAL_ASSET_POOL,
			Business.PECD,
			Business.PRIME_FINANCE,
			Business.PRIMERICA_FINANCIAL_SERVICES,
			Business.PRIVATE_BANKING,
			Business.PROJECT_FINANCE, // Start
			Business.RATES_AND_CURRENCIES,
			Business.REAL_ESTATE_LENDING,
			Business.RETAIL_AUTO_LENDING,
			Business.RETAIL_BANKING,
			Business.RETAIL_PARTNER_CARDS,
			Business.RISK_TREASURY,
			Business.RUBICON_INDIA,
			Business.SAP_ADMIN,
			Business.SECURITIZED_MARKETS,
			Business.SHORT_TERM,
			Business.SMITH_BARNEY_BAM,
			Business.STUDENT_LOANS,
			Business.US_COMMERCIAL_BANKING,
			Business.US_CONSUMER_INSTALLMENT_LOANS,
		};

		System.out.println ("\t|-----------------------------------------------------------------------------------------------------------||");

		System.out.println ("\t|                           BUSINESS HIERARCHY - ACCOUNTS BELONGING TO A BUSINESS                           ||");

		System.out.println ("\t|-----------------------------------------------------------------------------------------------------------||");

		System.out.println ("\t|        L -> R:                                                                                            ||");

		System.out.println ("\t|                - Business                                                                                 ||");

		System.out.println ("\t|                - Account Set Count                                                                        ||");

		System.out.println ("\t|                - Account Set                                                                              ||");

		System.out.println ("\t|-----------------------------------------------------------------------------------------------------------||");

		for (String business : businessArray)
		{
			ListAccountSet (business);
		}

		System.out.println ("\t|-----------------------------------------------------------------------------------------------------------||");

		EnvManager.TerminateEnv();
	}
}
