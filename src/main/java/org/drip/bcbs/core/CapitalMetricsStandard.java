
package org.drip.bcbs.core;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, valuation adjustment, and portfolio construction within and across fixed income,
 *  	credit, commodity, equity, FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three modules:
 *  
 *  - DROP Analytics Core - https://lakshmidrip.github.io/DROP-Analytics-Core/
 *  - DROP Portfolio Core - https://lakshmidrip.github.io/DROP-Portfolio-Core/
 *  - DROP Numerical Core - https://lakshmidrip.github.io/DROP-Numerical-Core/
 * 
 * 	DROP Analytics Core implements libraries for the following:
 * 	- Fixed Income Analytics
 * 	- Asset Backed Analytics
 * 	- XVA Analytics
 * 	- Exposure and Margin Analytics
 * 
 * 	DROP Portfolio Core implements libraries for the following:
 * 	- Asset Allocation Analytics
 * 	- Transaction Cost Analytics
 * 
 * 	DROP Numerical Core implements libraries for the following:
 * 	- Statistical Learning
 * 	- Numerical Optimizer
 * 	- Spline Builder
 * 	- Algorithm Support
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
 * <i>CapitalMetrics</i> holds the Realized Capital Metrics. The References are:
 * 
 * <br><br>
 * 	<ul>
 * 		<li>
 * 			Basel Committee on Banking Supervision (2017): Basel III Leverage Ratio Framework and Disclosure
 * 				Requirements https://www.bis.org/publ/bcbs270.pdf
 * 		</li>
 * 		<li>
 * 			Central Banking (2013): Fed and FDIC agree 6% Leverage Ratio for US SIFIs
 * 				https://www.centralbanking.com/central-banking/news/2280726/fed-and-fdic-agree-6-leverage-ratio-for-us-sifis
 * 		</li>
 * 		<li>
 * 			European Banking Agency (2013): Implementing Basel III in Europe: CRD IV Package
 * 				https://eba.europa.eu/regulation-and-policy/implementing-basel-iii-europe
 * 		</li>
 * 		<li>
 * 			Federal Reserve (2013): Liquidity Coverage Ratio � Liquidity Risk Measurements, Standards, and
 * 				Monitoring
 * 				https://web.archive.org/web/20131102074614/http:/www.federalreserve.gov/FR_notice_lcr_20131024.pdf
 * 		</li>
 * 		<li>
 * 			Wikipedia (2018): Basel III https://en.wikipedia.org/wiki/Basel_III
 * 		</li>
 * 	</ul>
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/AnalyticsCore.md">Analytics Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/ExposureAnalyticsLibrary.md">Exposure Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/bcbs/README.md">BCBS</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/bcbs/core/README.md">Core</a></li>
 *  </ul>
 * 
 * @author Lakshmi Krishnamurthy
 */

public class CapitalMetricsStandard extends org.drip.bcbs.core.CapitalMetrics
{
	private double _cet1DeductionsPhaseIn = java.lang.Double.NaN;

	/**
	 * Construct the Basel III 2013 Version of the Capital Metrics Standard
	 * 
	 * @return Basel III 2013 Version of the Capital Metrics Standard
	 */

	public static final CapitalMetricsStandard Basel_III_2013()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.030,
				0.035,
				0.035,
				0.045,
				0.045,
				0.045,
				0.00
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Basel III 2014 Version of the Capital Metrics Standard
	 * 
	 * @return Basel III 2014 Version of the Capital Metrics Standard
	 */

	public static final CapitalMetricsStandard Basel_III_2014()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.030,
				0.040,
				0.040,
				0.055,
				0.080,
				0.080,
				0.20
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Basel III 2015 Version of the Capital Metrics Standard
	 * 
	 * @return Basel III 2015 Version of the Capital Metrics Standard
	 */

	public static final CapitalMetricsStandard Basel_III_2015()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.030,
				0.045,
				0.045,
				0.060,
				0.080,
				0.080,
				0.40
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Basel III 2016 Version of the Capital Metrics Standard
	 * 
	 * @return Basel III 2016 Version of the Capital Metrics Standard
	 */

	public static final CapitalMetricsStandard Basel_III_2016()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.03000,
				0.04500,
				0.05125,
				0.06000,
				0.08000,
				0.08625,
				0.60
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Basel III 2017 Version of the Capital Metrics Standard
	 * 
	 * @return Basel III 2017 Version of the Capital Metrics Standard
	 */

	public static final CapitalMetricsStandard Basel_III_2017()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.03000,
				0.04500,
				0.05750,
				0.06000,
				0.08000,
				0.09250,
				0.80
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Basel III 2018 Version of the Capital Metrics Standard
	 * 
	 * @return Basel III 2018 Version of the Capital Metrics Standard
	 */

	public static final CapitalMetricsStandard Basel_III_2018()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.03000,
				0.04500,
				0.06375,
				0.06000,
				0.08000,
				0.09875,
				1.00
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Basel III 2019 Version of the Capital Metrics Standard
	 * 
	 * @return Basel III 2019 Version of the Capital Metrics Standard
	 */

	public static final CapitalMetricsStandard Basel_III_2019()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.03000,
				0.04500,
				0.07000,
				0.06000,
				0.08000,
				0.10500,
				1.00
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Federal Reserve's Version of the Capital Metrics Standard for Systemically Important
	 * 	Financial Institutions (SIFI)
	 * 
	 * @return Federal Reserve's Version of the Capital Metrics Standard for Systemically Important Financial
	 * 	Institutions (SIFI)
	 */

	public static final CapitalMetricsStandard US_SIFI()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.06000,
				0.04500,
				0.07000,
				0.06000,
				0.08000,
				0.10500,
				1.00
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Construct the Federal Reserve's Version of the Capital Metrics Standard for Systemically Important
	 * 	Financial Institutions' Insured Bank Holding Corporations (BHC)
	 * 
	 * @return Federal Reserve's Version of the Capital Metrics Standard for Systemically Important Financial
	 * 	Institutions' Insured Bank Holding Corporations (BHC)
	 */

	public static final CapitalMetricsStandard US_SIFI_BHC()
	{
		try
		{
			return new CapitalMetricsStandard (
				0.05000,
				0.04500,
				0.07000,
				0.06000,
				0.08000,
				0.10500,
				1.00
			);
		}
		catch (java.lang.Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * CapitalMetricsStandard Constructor
	 * 
	 * @param leverageRatio Leverage Ratio
	 * @param commonEquityRatio Common Equity Capital Ratio
	 * @param commonEquityPlusConservationBufferRatio Common Equity Capital Plus Capital Conservation Buffer
	 * 		Ratio
	 * @param tier1Ratio Tier 1 Capital Ratio
	 * @param totalRatio Total Capital Ratio
	 * @param totalPlusConservationBufferRatio Total Capital Plus Conservation Buffer Ratio
	 * @param cet1DeductionsPhaseIn CET1 Deductions Phase-in
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public CapitalMetricsStandard (
		final double leverageRatio,
		final double commonEquityRatio,
		final double commonEquityPlusConservationBufferRatio,
		final double tier1Ratio,
		final double totalRatio,
		final double totalPlusConservationBufferRatio,
		final double cet1DeductionsPhaseIn)
		throws java.lang.Exception
	{
		super (
			leverageRatio,
			commonEquityRatio,
			commonEquityPlusConservationBufferRatio,
			tier1Ratio,
			totalRatio,
			totalPlusConservationBufferRatio
		);

		if (!org.drip.quant.common.NumberUtil.IsValid (_cet1DeductionsPhaseIn = cet1DeductionsPhaseIn) ||
			0. > _cet1DeductionsPhaseIn)
		{
			throw new java.lang.Exception ("CapitalMetricsStandard Contructor => Invalid Inputs");
		}
	}

	/**
	 * Retrieve the CET1 Deductions Phase-in
	 * 
	 * @return The CET1 Deductions Phase-in
	 */

	public double cet1DeductionsPhaseIn()
	{
		return _cet1DeductionsPhaseIn;
	}
}
