
package org.drip.param.definition;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2019 Lakshmi Krishnamurthy
 * Copyright (C) 2018 Lakshmi Krishnamurthy
 * Copyright (C) 2017 Lakshmi Krishnamurthy
 * Copyright (C) 2016 Lakshmi Krishnamurthy
 * Copyright (C) 2015 Lakshmi Krishnamurthy
 * Copyright (C) 2014 Lakshmi Krishnamurthy
 * Copyright (C) 2013 Lakshmi Krishnamurthy
 * Copyright (C) 2012 Lakshmi Krishnamurthy
 * Copyright (C) 2011 Lakshmi Krishnamurthy
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
 * <i>ProductQuote</i> abstract class holds the different types of quotes for a given product. It contains a
 * single market field/quote pair, but multiple alternate named quotes (to accommodate quotes on different
 * measures for the component).
 * 
 * <br><br>
 *  <ul>
 *		<li><b>Module</b>        = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/param">Param</a></li>
 *		<li><b>Package</b>       = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/src/main/java/org/drip/param/definition">Definition</a></li>
 *		<li><b>Specification</b> = <a href = "https://github.com/lakshmiDRIP/DROP/tree/master/Docs/Internal/FixedIncome">Fixed Income Analytics</a></li>
 *  </ul>
 * <br><br>
 *
 * @author Lakshmi Krishnamurthy
 */

public abstract class ProductQuote {

	/**
	 * Add a regular or a market quote for the component
	 * 
	 * @param strQuoteField The quote field
	 * @param q Quote to be added
	 * @param bIsMarketQuote Whether the quote is a market quote
	 */

	public abstract void addQuote (
		final java.lang.String strQuoteField,
		final org.drip.param.definition.Quote q,
		final boolean bIsMarketQuote);

	/**
	 * Set the market quote for the component
	 * 
	 * @param strMarketQuoteField Market Quote field
	 * @param q Quote
	 * 
	 * @return True if successfully added
	 */

	public abstract boolean setMarketQuote (
		final java.lang.String strMarketQuoteField,
		final org.drip.param.definition.Quote q);

	/**
	 * Remove the market quote
	 * 
	 * @return TRUE - The Market Quote has been removed
	 */

	public abstract boolean removeMarketQuote();

	/**
	 * Get the Quote for the given Field
	 * 
	 * @param strQuoteField Field Name
	 * 
	 * @return Quote object
	 */

	public abstract org.drip.param.definition.Quote quote (
		final java.lang.String strQuoteField);

	/**
	 * Return the market quote object
	 * 
	 * @return Quote object
	 */

	public abstract org.drip.param.definition.Quote marketQuote();

	/**
	 * Retrieve the market quote field
	 * 
	 * @return Field name
	 */

	public abstract java.lang.String marketQuoteField();

	/**
	 * Remove the named Quote
	 * 
	 * @param strQuoteField Named Quote Field
	 * 
	 * @return Success (true) or failure (false)
	 */

	public abstract boolean removeQuote (
		final java.lang.String strQuoteField);

	/**
	 * Indicate if the named quote is available
	 * 
	 * @param strQuoteField The Quote Name
	 * 
	 * @return TRUE - Named Quote is present
	 */

	public abstract boolean containsQuote (
		final java.lang.String strQuoteField);
}
