
package org.drip.dynamics.hjm;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * 
 *  This file is part of DROP, an open-source library targeting risk, transaction costs, exposure, margin
 *  	calculations, and portfolio construction within and across fixed income, credit, commodity, equity,
 *  	FX, and structured products.
 *  
 *  	https://lakshmidrip.github.io/DROP/
 *  
 *  DROP is composed of three main modules:
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
 * 	- Statistical Learning Library
 * 	- Numerical Optimizer Library
 * 	- Machine Learning Library
 * 	- Spline Builder Library
 * 
 * 	Documentation for DROP is Spread Over:
 * 
 * 	- Main                     => https://lakshmidrip.github.io/DROP/
 * 	- Wiki                     => https://github.com/lakshmiDRIP/DROP/wiki
 * 	- GitHub                   => https://github.com/lakshmiDRIP/DROP
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
 * <i>MultiFactorVolatility</i> implements the Volatility of the Multi-factor Stochastic Evolution Process.
 * The Factors may come from the Underlying Stochastic Variables, or from Principal Components.
 *
 *	<br><br>
 *  <ul>
 *		<li><b>Module </b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/AnalyticsCore.md">Analytics Core Module</a></li>
 *		<li><b>Library</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/FixedIncomeAnalyticsLibrary.md">Fixed Income Analytics</a></li>
 *		<li><b>Project</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/dynamics">Dynamics</a></li>
 *		<li><b>Package</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/dynamics/hjm">HJM</a></li>
 *  </ul>
 *
 * @author Lakshmi Krishnamurthy
 */

public class MultiFactorVolatility {
	private org.drip.analytics.definition.MarketSurface[] _aMSVolatility = null;
	private org.drip.sequence.random.PrincipalFactorSequenceGenerator _pfsg = null;

	/**
	 * MultiFactorVolatility Constructor
	 * 
	 * @param aMSVolatility Array of the Multi-Factor Volatility Surfaces
	 * @param pfsg Principal Factor Sequence Generator
	 * 
	 * @throws java.lang.Exception Thrown if Inputs are Invalid
	 */

	public MultiFactorVolatility (
		final org.drip.analytics.definition.MarketSurface[] aMSVolatility,
		final org.drip.sequence.random.PrincipalFactorSequenceGenerator pfsg)
		throws java.lang.Exception
	{
		if (null == (_aMSVolatility = aMSVolatility) || null == (_pfsg = pfsg))
			throw new java.lang.Exception ("MultiFactorVolatility ctr: Invalid Inputs");

		int iNumFactor = _pfsg.numFactor();

		if (0 == iNumFactor || _aMSVolatility.length < iNumFactor)
			throw new java.lang.Exception ("MultiFactorVolatility ctr: Invalid Inputs");
	}

	/**
	 * Retrieve the Array of Volatility Surfaces
	 * 
	 * @return The Array of Volatility Surfaces
	 */

	public org.drip.analytics.definition.MarketSurface[] volatilitySurface()
	{
		return _aMSVolatility;
	}

	/**
	 * Retrieve the Principal Factor Sequence Generator
	 * 
	 * @return The Principal Factor Sequence Generator
	 */

	public org.drip.sequence.random.PrincipalFactorSequenceGenerator msg()
	{
		return _pfsg;
	}

	/**
	 * Retrieve the Factor-Specific Univariate Volatility Function for the Specified Date
	 * 
	 * @param iFactorIndex The Factor Index
	 * @param iXDate The X Date
	 * 
	 * @return The Factor-Specific Univariate Volatility Function for the Specified Date
	 */

	public org.drip.function.definition.R1ToR1 xDateVolatilityFunction (
		final int iFactorIndex,
		final int iXDate)
	{
		int iNumFactor = _pfsg.numFactor();

		if (iFactorIndex >= iNumFactor) return null;

		final int iNumVariate = _aMSVolatility.length;

		return new org.drip.function.definition.R1ToR1 (null) {
			@Override public double evaluate (
				final double dblX)
				throws java.lang.Exception
			{
				double dblMultiFactorVol = 0.;

				double[] adblFactor = _pfsg.factors()[iFactorIndex];

				for (int i = 0; i < iNumVariate; ++i) {
					org.drip.analytics.definition.NodeStructure tsVolatilityXDate =
						_aMSVolatility[iFactorIndex].xAnchorTermStructure (iXDate);

					dblMultiFactorVol += adblFactor[i] * tsVolatilityXDate.node ((int) dblX);
				}

				return _pfsg.factorWeight()[iFactorIndex] * dblMultiFactorVol;
			}
		};
	}

	/**
	 * Compute the Factor Volatility Integral
	 * 
	 * @param iFactorIndex The Factor Index
	 * @param iXDate The X Date
	 * @param iYDate The Y Date
	 * 
	 * @return The Factor Volatility Integral
	 * 
	 * @throws java.lang.Exception Thrown if the Factor Volatility Integral cannot be computed
	 */

	public double volatilityIntegral (
		final int iFactorIndex,
		final int iXDate,
		final int iYDate)
		throws java.lang.Exception
	{
		org.drip.function.definition.R1ToR1 auVolatilityFunction = xDateVolatilityFunction (iFactorIndex,
			iXDate);

		if (null == auVolatilityFunction)
			throw new java.lang.Exception
				("MultiFactorVolatility::volatilityIntegral => Cannot extract X Date Volatility Function");

		return auVolatilityFunction.integrate (iXDate, iYDate) / 365.25;
	}

	/**
	 * Compute the Factor Point Volatility
	 * 
	 * @param iFactorIndex The Factor Index
	 * @param iXDate The X Date
	 * @param iYDate The Y Date
	 * 
	 * @return The Factor Point Volatility
	 * 
	 * @throws java.lang.Exception Thrown if the Factor Point Volatility cannot be computed
	 */

	public double factorPointVolatility (
		final int iFactorIndex,
		final int iXDate,
		final int iYDate)
		throws java.lang.Exception
	{
		int iNumFactor = _pfsg.numFactor();

		if (iFactorIndex >= iNumFactor)
			throw new java.lang.Exception
				("MultiFactorVolatility::factorPointVolatility => Invalid Factor Index");

		double[] adblFactor = _pfsg.factors()[iFactorIndex];

		int iNumVariate = adblFactor.length;
		double dblFactorPointVolatility = 0.;

		for (int i = 0; i < iNumVariate; ++i)
			dblFactorPointVolatility += adblFactor[i] * _aMSVolatility[i].node (iXDate, iYDate);

		return dblFactorPointVolatility;
	}

	/**
	 * Compute the Array of Factor Point Volatilities
	 * 
	 * @param iXDate The X Date
	 * @param iYDate The Y Date
	 * 
	 * @return The Array of Factor Point Volatilities
	 */

	public double[] factorPointVolatility (
		final int iXDate,
		final int iYDate)
	{
		int iNumFactor = _pfsg.numFactor();

		double[][] aadblFactor = _pfsg.factors();

		int iNumVariate = aadblFactor[0].length;
		double[] adblVariateVolatility = new double[iNumVariate];
		double[] adblFactorPointVolatility = new double[iNumFactor];

		for (int iVariateIndex = 0; iVariateIndex < iNumVariate; ++iVariateIndex) {
			try {
				adblVariateVolatility[iVariateIndex] = _aMSVolatility[iVariateIndex].node (iXDate, iYDate);
			} catch (java.lang.Exception e) {
				e.printStackTrace();

				return null;
			}
		}

		for (int iFactorIndex = 0; iFactorIndex < iNumFactor; ++iFactorIndex) {
			adblFactorPointVolatility[iFactorIndex] = 0.;
			double[] adblFactor = aadblFactor[iFactorIndex];

			for (int iVariateIndex = 0; iVariateIndex < iNumVariate; ++iVariateIndex)
				adblFactorPointVolatility[iFactorIndex] += adblFactor[iVariateIndex] *
					adblVariateVolatility[iVariateIndex];
		}

		return adblFactorPointVolatility;
	}

	/**
	 * Compute the Weighted Factor Point Volatility
	 * 
	 * @param iFactorIndex The Factor Index
	 * @param iXDate The X Date
	 * @param iYDate The Y Date
	 * 
	 * @return The Weighted Factor Point Volatility
	 * 
	 * @throws java.lang.Exception Thrown if the Weighted Factor Point Volatility cannot be computed
	 */

	public double weightedFactorPointVolatility (
		final int iFactorIndex,
		final int iXDate,
		final int iYDate)
		throws java.lang.Exception
	{
		int iNumFactor = _pfsg.numFactor();

		if (iFactorIndex >= iNumFactor)
			throw new java.lang.Exception
				("MultiFactorVolatility::weightedFactorPointVolatility => Invalid Factor Index");

		double[] adblFactor = _pfsg.factors()[iFactorIndex];

		int iNumVariate = adblFactor.length;
		double dblFactorPointVolatility = 0.;

		for (int i = 0; i < iNumVariate; ++i)
			dblFactorPointVolatility += adblFactor[i] * _aMSVolatility[i].node (iXDate, iYDate);

		return _pfsg.factorWeight()[iFactorIndex] * dblFactorPointVolatility;
	}

	/**
	 * Compute the Point Volatility Modulus
	 * 
	 * @param iXDate The X Date
	 * @param iYDate The Y Date
	 * 
	 * @return The Point Volatility Modulus
	 * 
	 * @throws java.lang.Exception Thrown if the Point Volatility Modulus cannot be computed
	 */

	public double pointVolatilityModulus (
		final int iXDate,
		final int iYDate)
		throws java.lang.Exception
	{
		int iNumFactor = _pfsg.numFactor();

		double dblPointVolatilityModulus = 0.;

		for (int i = 0; i < iNumFactor; ++i) {
			double dblWeightedFactorPointVolatility = weightedFactorPointVolatility (i, iXDate, iYDate);

			dblPointVolatilityModulus += dblWeightedFactorPointVolatility * dblWeightedFactorPointVolatility;
		}

		return dblPointVolatilityModulus;
	}

	/**
	 * Compute the Point Volatility Modulus Derivative
	 * 
	 * @param iXDate The X Date
	 * @param iYDate The Y Date
	 * @param iOrder The Derivative Order
	 * @param bTerminal TRUE - Derivative off of the Y Date; FALSE - Derivative off of the X Date
	 * 
	 * @return The Point Volatility Modulus Derivative
	 * 
	 * @throws java.lang.Exception Thrown if the Point Volatility Modulus Derivative cannot be computed
	 */

	public double pointVolatilityModulusDerivative (
		final int iXDate,
		final int iYDate,
		final int iOrder,
		final boolean bTerminal)
		throws java.lang.Exception
	{
		org.drip.function.definition.R1ToR1 pointVolatilityR1ToR1 = new
			org.drip.function.definition.R1ToR1 (null) {
			@Override public double evaluate (
				final double dblVariate)
				throws java.lang.Exception
			{
				return bTerminal ? pointVolatilityModulus (iXDate, (int) dblVariate) : pointVolatilityModulus
					((int) dblVariate, iYDate);
			}
		};

		return bTerminal ? pointVolatilityR1ToR1.derivative (iXDate, iOrder) :
			pointVolatilityR1ToR1.derivative (iXDate, iOrder);
	}
}
